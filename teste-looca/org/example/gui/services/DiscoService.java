package org.example.gui.services;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import org.example.gui.components.Componente;
import org.example.gui.components.Registro;
import org.example.gui.conexao.Conexao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class DiscoService {

    private List<Disco> listaDiscos;
    private List<Componente> dadosDiscos;
    private Componente componente;
    private String registro;
    private Conexao conexao;
    private JdbcTemplate con;
    private Looca looca;

    public DiscoService() {
        this.listaDiscos = new ArrayList();
        this.dadosDiscos = new ArrayList();
        this.componente = new Componente();
        this.conexao = new Conexao();
        this.con = this.conexao.getConexaoDoBanco();
        this.looca = new Looca();
    }
    public void cadastrarDisco(Integer fk){
        DecimalFormat df = new DecimalFormat("0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(java.util.Locale.US));
        this.dadosDiscos = con.query("SELECT * FROM componente WHERE fk_usuario = %d AND tipo = 'DISCO'".formatted(fk), new BeanPropertyRowMapper<>(Componente.class));
        this.listaDiscos = looca.getGrupoDeDiscos().getDiscos();
        if(this.listaDiscos.size() != this.dadosDiscos.size()) {
            for (int i = dadosDiscos.size(); i < listaDiscos.size(); i++) {
                componente.setTipo("DISCO");
                componente.setNome(listaDiscos.get(i).getNome());
                componente.setCapacidade(listaDiscos.get(i).getTamanho() / Math.pow(10, 9));
                componente.setNum_nucleo(null);
                componente.setVelocidade(null);
                con.update("INSERT INTO componente (fk_usuario, nome, tipo, num_nucleo, capacidade, velocidade) VALUES ( %d,'%s', '%s', NULL, %s, NULL)".formatted(fk, componente.getNome(),componente.getTipo(), df.format(componente.getCapacidade())));
            }
            this.dadosDiscos = con.query("SELECT * FROM componente WHERE fk_usuario = %d AND tipo = 'DISCO'".formatted(fk), new BeanPropertyRowMapper<>(Componente.class));
            System.out.println("""
                                   Inseri o disco
                                   nome: %s
                                   fk_usuario: %d
                                   tipo: %s
                                   num_nucle0: null
                                   capacidade: %s
                                   velocidade: null
                                   """.formatted(componente.getNome(), fk, componente.getTipo(), df.format(componente.getCapacidade())));
        }
    }
    public void capturarDadosDisco(){
        DecimalFormat df = new DecimalFormat("0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(java.util.Locale.US));

        Disco disco;
        Double vel_escr;
        Double vel_leit;
        Double uso;
        Integer idDisco;

        for (int i = 0; i < dadosDiscos.size(); i++) {
            disco = looca.getGrupoDeDiscos().getDiscos().get(i);

            idDisco = dadosDiscos.get(i).getId_componente();

            Long bytesLidosInicio = disco.getBytesDeLeitura();
            Long bytesEscrInicio = disco.getBytesDeEscritas();

            try {
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            disco = looca.getGrupoDeDiscos().getDiscos().get(i);

            Long bytesEscrFim = disco.getBytesDeEscritas();
            Long bytesLidosFim = disco.getBytesDeLeitura();

            Long leituras = disco.getLeituras();
            Long escritas = disco.getEscritas();

            Long tamanhoAtualDaFila = disco.getTamanhoAtualDaFila();
            Long tempoDeTransferencia = disco.getTempoDeTransferencia();
            Double tempoEmSegundos = tempoDeTransferencia / 1000.0;


            if (tempoDeTransferencia != 0 && tamanhoAtualDaFila !=0) {
                uso = ((leituras + escritas) / (tamanhoAtualDaFila * tempoDeTransferencia)) * 100.0;
                System.out.println(uso);
            } else {
                List<Registro> listaAntigosRegistros = con.query("SELECT * FROM registro WHERE fk_componente = %d ORDER BY id_registro DESC LIMIT 1".formatted(idDisco), new BeanPropertyRowMapper<>(Registro.class));
                if (listaAntigosRegistros.size() > 0){
                    uso = listaAntigosRegistros.get(0).getUso();
                } else {
                    uso = 0.0;
                }
            }

            Long tamanhoTotal = disco.getTamanho();

            Long espacoEmUso = bytesLidosFim + bytesEscrFim;

            Long espacoDisponivel = tamanhoTotal - espacoEmUso;

            Double espacoEmUsoGB = espacoEmUso / (1024.0 * 1024.0 * 1024.0);
            Double espacoDisponivelGB = espacoDisponivel / (1024.0 * 1024.0 * 1024.0);

            vel_escr = ((bytesEscrFim - bytesEscrInicio)/ 1024.0)/ tempoEmSegundos;
            vel_leit = ((bytesLidosFim - bytesLidosInicio)/ 1024.0)/ tempoEmSegundos;

            con.update("INSERT INTO registro (fk_componente, uso, vel_grav, vel_leit, vel_cpu, mem_uso,mem_disp, dt_hora) VALUES (%d, %s, %s, %s, null, %s, %s, now() )".formatted(idDisco, df.format(uso), df.format(vel_escr), df.format(vel_leit), df.format(espacoEmUsoGB), df.format(espacoDisponivelGB)));
        }
    }
}
