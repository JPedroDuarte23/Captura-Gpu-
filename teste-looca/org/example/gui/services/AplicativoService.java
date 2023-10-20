package org.example.gui.services;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import org.example.gui.components.Aplicativo;
import org.example.gui.components.Componente;
import org.example.gui.conexao.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class AplicativoService {
    private Componente componente;
    private Conexao conexao;
    private JdbcTemplate con;
    private Looca looca;
    private Processo processo;
    private Janela janela;
    public AplicativoService(){
        this.componente = new Componente();
        this.conexao = new Conexao();
        this.con = this.conexao.getConexaoDoBanco();
        this.looca = new Looca();
    }
    public void capturarAplicativos(){

        Looca looca = new Looca();
        List<Janela> listaJanelas = new ArrayList();
        List<Processo> listaProcessos = new ArrayList();

        DecimalFormat df = new DecimalFormat("0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(java.util.Locale.US));

        ProcessoGrupo processoGrupo = looca.getGrupoDeProcessos();
        JanelaGrupo janelaGrupo = looca.getGrupoDeJanelas();
        listaJanelas = janelaGrupo.getJanelas();
        listaProcessos = processoGrupo.getProcessos();
        Aplicativo aplicativo;
        int PIDProcesso;
        int PIDJanela;
        long longPIDJanela;

        for (int i = 0; i < listaProcessos.size(); i++) {
            PIDProcesso = listaProcessos.get(i).getPid();
            for (int j = 0; j < listaJanelas.size(); j++) {
                longPIDJanela = listaJanelas.get(j).getPid();
                PIDJanela = (int) longPIDJanela;
                if(PIDJanela == PIDProcesso){
                    this.processo = listaProcessos.get(i);
                    this.janela = listaJanelas.get(j);
//                    System.out.println(listaProcessos.get(i));
//                    System.out.println(listaJanelas.get(j));
                    aplicativo = new Aplicativo();
                    aplicativo.setPID(PIDJanela);

                    con.update("INSERT INTO aplicativos_abertos (pid, titulo, comando, usoCPU, usoMemoria,dt_hora) VALUES (%d, '%s', '%s', %s, %s, now() )".formatted(aplicativo.getPID(), janela.getTitulo(), janela.getComando(), df.format(processo.getUsoCpu()), df.format(processo.getUsoMemoria())));
//                    System.out.println("INSERT INTO aplicativos_abertos (pid, titulo, comando, usoCPU, usoMemoria,dt_hora) VALUES (%d, '%s', '%s', %s, %s, now() )".formatted(aplicativo.getPID(), janela.getTitulo(), janela.getComando(), df.format(processo.getUsoCpu()), df.format(processo.getUsoMemoria())));;
                }
            }
        }
    }
}


