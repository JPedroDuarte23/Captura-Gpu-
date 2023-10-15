package org.example.gui.services;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import org.example.gui.components.Componente;
import org.example.gui.components.Registro;
import org.example.gui.conexao.Conexao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.driver.mac.net.NetStat;

import java.text.DecimalFormatSymbols;
import java.util.List;
import java.text.DecimalFormat;

public class CpuService {
    private Componente componente;
    private List<Componente> dadosProcessador;
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
        DecimalFormat df = new DecimalFormat("0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(java.util.Locale.US));

        this.dadosProcessador = con.query("SELECT * FROM componente WHERE fk_usuario = %d AND tipo = 'CPU'".formatted(fk), new BeanPropertyRowMapper<>(Componente.class));
        this.cpu = looca.getProcessador();

            if(!this.cpu.getNome().equals(componente.getNome())){

                componente.setTipo("CPU");
                componente.setNome(cpu.getNome());
                componente.setCapacidade(null);
                componente.setNum_nucleo(cpu.getNumeroCpusFisicas() + cpu.getNumeroCpusLogicas());
                componente.setVelocidade(cpu.getFrequencia() / Math.pow(10,9));

                if(dadosProcessador.size() <= 0){
                    con.update("INSERT INTO componente (fk_usuario, nome, tipo, num_nucleo, capacidade, velocidade) VALUES (%d, '%s', '%s', %d, null, %s)".formatted(fk, componente.getNome(),componente.getTipo(), componente.getNum_nucleo(), df.format(componente.getVelocidade())));
                    System.out.println("""
                                       Inseri a cpu
                                       nome: %s
                                       fk_usuario: %d
                                       tipo: %s
                                       num_nucle0: %d
                                       capacidade: null
                                       velocidade: %s
                                       """.formatted(componente.getNome(), fk, componente.getTipo(), componente.getNum_nucleo(), df.format(componente.getVelocidade())));
                } else{
                    con.update("UPDATE componente SET nome = '%s', num_nucleo = %d, velocidade = %.2f WHERE idComponente = %d".formatted(componente.getNome(), componente.getNum_nucleo(), df.format(componente.getVelocidade()), dadosProcessador.get(0).getId_componente()));
                }
                this.dadosProcessador = con.query("SELECT * FROM componente WHERE fk_usuario = %d AND tipo = 'CPU'".formatted(fk), new BeanPropertyRowMapper<>(Componente.class));
            }
    }
    public void capturarDadosProcessador(){
        DecimalFormat df = new DecimalFormat("0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(java.util.Locale.US));
        System.out.println(dadosProcessador);
        Integer fk_componente = dadosProcessador.get(0).getId_componente();
        this.cpu = looca.getProcessador();
        Double uso = cpu.getUso();
        Double vel_cpu = cpu.getFrequencia() / Math.pow(10,9);
        System.out.println("INSERT INTO registro (fk_componente, uso, vel_grav, vel_leit, vel_cpu, mem_uso, mem_disp, dt_hora) VALUES (%d, %s, null, null, %s, null, null, now() )".formatted(fk_componente,df.format(uso), df.format(vel_cpu)));
        con.update("INSERT INTO registro (fk_componente, uso, vel_grav, vel_leit, vel_cpu, mem_uso, mem_disp, dt_hora) VALUES (%d, %s, null, null, %s, null, null, now() )".formatted(fk_componente,df.format(uso), df.format(vel_cpu)));
    }
}