package org.example.gui.services;

import  com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import org.example.gui.components.Componente;
import org.example.gui.conexao.Conexao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class MemoriaService {
    private Componente componente;
    private List<Componente> dadosMemoria;
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
    public void cadastrarMemoria(Integer fk){
        DecimalFormat df = new DecimalFormat("0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(java.util.Locale.US));
        this.dadosMemoria = con.query("SELECT * FROM componente WHERE fk_usuario = %d AND tipo = 'RAM'".formatted(fk), new BeanPropertyRowMapper<>(Componente.class));
        this.memoria = looca.getMemoria();
        if(this.dadosMemoria.size() < 1 ||
                this.dadosMemoria.get(0).getCapacidade() != this.memoria.getTotal() / Math.pow(10,9)) {

            componente.setTipo("RAM");
            componente.setNome("Memória RAM");
            componente.setCapacidade(this.memoria.getTotal() / Math.pow(10, 9));
            componente.setNum_nucleo(null);
            componente.setVelocidade(null);
            con.update("INSERT INTO componente (fk_usuario, nome, tipo, num_nucleo, capacidade, velocidade) VALUES (%d, '%s', '%s', NULL, %s, NULL)".formatted(fk ,componente.getNome(),componente.getTipo(), df.format(componente.getCapacidade())));
        }
        this.dadosMemoria = con.query("SELECT * FROM componente WHERE fk_usuario = %d AND tipo = 'RAM'".formatted(fk), new BeanPropertyRowMapper<>(Componente.class));
        System.out.println("""
                                   Inseri a memoria
                                   nome: %s
                                   fk_usuario: %d
                                   tipo: %s
                                   num_nucle0: null
                                   capacidade: %s
                                   velocidade: null
                                   """.formatted(componente.getNome(), fk, componente.getTipo(), df.format(componente.getCapacidade())));
    }
    public void capturarDadosMemoria(){
        DecimalFormat df = new DecimalFormat("0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(java.util.Locale.US));

        Integer idMemoria = dadosMemoria.get(0).getId_componente();

        Memoria memoria = looca.getMemoria();
        Double mem_uso = memoria.getEmUso() / (1024.0 * 1024.0 * 1024.0);
        Double mem_disp = memoria.getDisponivel() / (1024.0 * 1024.0 * 1024.0);
        Double mem_total = memoria.getTotal() / (1024.0 * 1024.0 * 1024.0);
        Double uso = (mem_uso / mem_total) * 100.0;

        con.update("INSERT INTO registro (fk_componente, uso, vel_grav, vel_leit, vel_cpu, mem_uso, mem_disp, dt_hora) VALUES (%d, %s, null, null, null, %s, %s, now() )".formatted(idMemoria, df.format(uso), df.format(mem_uso), df.format(mem_disp)));
    }

}
