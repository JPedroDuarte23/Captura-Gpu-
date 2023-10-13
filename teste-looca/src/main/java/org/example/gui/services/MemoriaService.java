package org.example.gui.services;

import  com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import org.example.gui.components.Componente;
import org.example.gui.conexao.Conexao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class MemoriaService {
    private Componente componente;
    private String registroMemoria;
    private Conexao conexao;
    private JdbcTemplate con;
    private Looca looca;
    private Memoria memoria;

    public MemoriaService() {
        this.componente = new Componente();
        this.conexao = new Conexao();
        this.con = this.conexao.getConexaoDoBanco();
        this.looca = new Looca();
    }
    public void cadastrarDisco(Integer fk){
    List<Componente> dadosMemoria;
    dadosMemoria = con.query("SELECT * FROM componentes WHERE fk = '%d' AND tipo = 'RAM'".formatted(fk), new BeanPropertyRowMapper<>(Componente.class));
        this.memoria = looca.getMemoria();
        if(dadosMemoria.size() != 1 &&
                dadosMemoria.get(0).getCapacidade() == this.memoria.getTotal() / Math.pow(10,9)) {

            componente.setTipo("RAM");
            componente.setNome("Memória RAM");
            componente.setCapacidade(this.memoria.getTotal() / Math.pow(10, 9));
            componente.setNum_nucleo(null);
            componente.setVelocidade(null);
            con.update("INSERT INTO componente (nome, tipo, num_nucleo, capacidade, velocidade) VALUES ('%s', '%s', NULL, %.2f, NULL)".formatted(componente.getNome(),componente.getTipo(), componente.getCapacidade()));
        }
    }
    public void capturarDadosMemoria(){
            Memoria memoria = looca.getMemoria();
            Double mem_uso = memoria.getEmUso() / Math.pow(10, 9);
            Double mem_disp = memoria.getDisponivel() / Math.pow(10,9);
            Double uso = mem_uso/ (memoria.getTotal() / Math.pow(10,9));

            con.update("INSERT INTO registro (uso, vel_grav, vel_leit, vel_cpu, mem_uso, mem_disp, dt_hora) VALUES (%.2f, null, null, null, %.2f, %.2f, now() )".formatted(uso, mem_uso, mem_disp));
    }

}
