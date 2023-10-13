package org.example.gui.services;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import org.example.gui.components.Componente;
import org.example.gui.conexao.Conexao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public class DiscoService {

    private List<Disco> listaDiscos;
    private List<String> listaRegistros;
    private Componente componente;
    private String registro;
    private Conexao conexao;
    private JdbcTemplate con;
    private Looca looca;

    public DiscoService() {
        this.listaDiscos = new ArrayList();
        this.listaRegistros = new ArrayList();
        this.componente = new Componente();
        this.conexao = new Conexao();
        this.con = this.conexao.getConexaoDoBanco();
        this.looca = new Looca();
    }
    public void cadastrarDisco(Integer fk){
    List<Componente> dadosDiscos;
    dadosDiscos = con.query("SELECT * FROM componentes WHERE fk = '%d' AND tipo = 'DISCO'".formatted(fk), new BeanPropertyRowMapper<>(Componente.class));
        if(this.listaDiscos.size() != dadosDiscos.size()) {
            this.listaDiscos = looca.getGrupoDeDiscos().getDiscos();
            for (int i = 0; i < listaDiscos.size(); i++) {
                componente.setTipo("DISCO");
                componente.setNome(listaDiscos.get(i).getNome());
                componente.setCapacidade(listaDiscos.get(i).getTamanho() / Math.pow(10, 9));
                componente.setNum_nucleo(null);
                componente.setVelocidade(null);
                con.update("INSERT INTO componente (nome, tipo, num_nucleo, capacidade, velocidade) VALUES ('%s', '%s', NULL, %.2f, NULL)".formatted(componente.getNome(),componente.getTipo(), componente.getCapacidade()));
            }
        }
    }
    public void capturarDadosDisco(){
            Disco disco;
            Double vel_escr;
            Double vel_leit;
            Double uso;
        for (int i = 0; i < listaDiscos.size(); i++) {
            disco = looca.getGrupoDeDiscos().getDiscos().get(i);
            Long bytesLidosInicio = disco.getBytesDeLeitura();
            Long bytesEscrInicio = disco.getBytesDeEscritas();
            try {
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Long bytesEscrFim = disco.getBytesDeEscritas();
            Long bytesLidosFim = disco.getBytesDeLeitura();
            uso = disco.getTempoDeTransferencia() / 1000.0;
            vel_escr = (bytesEscrFim - bytesEscrInicio/1000.0);
            vel_leit = (bytesLidosFim - bytesLidosInicio/1000.0);
            con.update("INSERT INTO registro (uso, vel_grav, vel_leit, vel_cpu, mem_uso, mem_disp, dt_hora) VALUES ('%2.f', '%.2f', %.2f, null, null, null, now() )".formatted(uso, vel_escr, vel_leit));
        }
    }
}
