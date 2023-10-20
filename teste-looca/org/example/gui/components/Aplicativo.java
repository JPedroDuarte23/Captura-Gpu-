package org.example.gui.components;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;

import java.util.ArrayList;
import java.util.List;

public class Aplicativo {
    private Integer PID;
    private String titulo;
    private String comando;
    private String usoCPU;
    private String usoMemoria;
//    private String usoDisco;
//    private String usoGPU;
    private String dataHora;

    public Aplicativo(Integer PID, String titulo, String comando, String usoCPU, String usoMemoria, String dataHora) {
        this.PID = PID;
        this.titulo = titulo;
        this.comando = comando;
        this.usoCPU = usoCPU;
        this.usoMemoria = usoMemoria;
        this.dataHora = dataHora;
    }
    public Aplicativo(){}

    public Integer getPID() {
        return PID;
    }

    public void setPID(Integer PID) {
        this.PID = PID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getUsoCPU() {
        return usoCPU;
    }

    public void setUsoCPU(String usoCPU) {
        this.usoCPU = usoCPU;
    }

    public String getUsoMemoria() {
        return usoMemoria;
    }

    public void setUsoMemoria(String usoMemoria) {
        this.usoMemoria = usoMemoria;
    }

//    public String getUsoDisco() {
//        return usoDisco;
//    }
//
//    public void setUsoDisco(String usoDisco) {
//        this.usoDisco = usoDisco;
//    }
//
//    public String getUsoGPU() {
//        return usoGPU;
//    }
//
//    public void setUsoGPU(String usoGPU) {
//        this.usoGPU = usoGPU;
//    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return """ 
                Aplicativo: 
                PID: %d 
                Titulo: %s
                Comando: %s
                UsoCPU: %s
                UsoMemoria: %s
                dataHora: %s
                """.formatted(PID, titulo, comando, usoCPU, usoMemoria, dataHora);
    }
}
