package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Looca looca = new Looca();
        List listaJanelas = new ArrayList();
        List listanumeros = new ArrayList();

        JanelaGrupo janelaGrupo = looca.getGrupoDeJanelas();
        Janela janela;
        DiscoGrupo discos = looca.getGrupoDeDiscos();
        Disco disco = discos.getDiscos().get(0);
        System.out.println(disco.toString());
        listaJanelas = janelaGrupo.getJanelas();

        for (int i = 0; i < listaJanelas.size(); i++) {
            janela = janelaGrupo.getJanelas().get(i);
            if (janela.getTitulo().indexOf("Chrome") == -1){
                System.out.println("passou");
            } else {
                System.out.println("nao passou");
            }
        }

        // System.out.println(listaJanelas);
        System.out.println(listaJanelas.indexOf("s"));
        System.out.println(listanumeros.indexOf(2));

    }
}