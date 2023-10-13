package org.example.gui.services;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import org.example.gui.components.Componente;
import org.example.gui.conexao.Conexao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CpuService {
    private Componente componente;
    private String registroCpu;
    private Conexao conexao;
    private JdbcTemplate con;
    private Looca looca;
    private Processador cpu;

    public CpuService() {
        this.componente = new Componente();
        this.conexao = new Conexao();
        this.con = this.conexao.getConexaoDoBanco();
        this.looca = new Looca();
    }
    public void cadastrarProcessador(Integer fk){
        List<Componente> dadosProcessador;
        dadosProcessador = con.query("SELECT * FROM componentes WHERE fk = '%d' AND tipo = 'CPU'".formatted(fk), new BeanPropertyRowMapper<>(Componente.class));
        this.cpu = looca.getProcessador();
            if( this.cpu.getNome().equals(componente.getNome())){

            componente.setTipo("CPU");
            componente.setNome(cpu.getNome());
            componente.setCapacidade(null);
            componente.setNum_nucleo(cpu.getNumeroCpusFisicas() + cpu.getNumeroCpusLogicas());
            componente.setVelocidade(cpu.getFrequencia() / Math.pow(10,9));
            con.update("INSERT INTO componente (nome, tipo, num_nucleo, capacidade, velocidade) VALUES ('%s', '%s', NULL, %.2f, %.2f)".formatted(componente.getNome(),componente.getTipo(), componente.getNum_nucleo(), componente.getVelocidade()));
        }
    }
    public void capturarDadosProcessador(){
        Processador cpu = looca.getProcessador();
        Double uso = cpu.getUso();
        Double vel_cpu = cpu.getFrequencia() / Math.pow(10,9);

        con.update("INSERT INTO registro (uso, vel_grav, vel_leit, vel_cpu, mem_uso, mem_disp, dt_hora) VALUES (%.2f, null, null, %.2f, null, null, now() )".formatted(uso, vel_cpu));
    }
}
