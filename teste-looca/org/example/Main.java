package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import com.github.britooo.looca.api.group.processador.Processador;
import oshi.hardware.GraphicsCard;

import org.example.gui.components.Gpu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Looca looca = new Looca();
        List listaJanelas = new ArrayList();
        List listanumeros = new ArrayList();

        JanelaGrupo janelaGrupo = looca.getGrupoDeJanelas();
        Janela janela;
        listaJanelas = janelaGrupo.getJanelas();

        for (int i = 0; i < listaJanelas.size(); i++) {
            janela = janelaGrupo.getJanelas().get(i);
            if (janela.getTitulo().indexOf("Chrome") == -1){
                System.out.println("passou");
            } else {
                System.out.println("nao passou");
            }
        }

        System.out.println(listaJanelas.indexOf("s"));
        System.out.println(listanumeros.indexOf(2));
        int porcentagem = 0;
        long mem_uso = 0;
        long mem_disp = 0;
        try {
            Process process = Runtime.getRuntime().exec("nvidia-smi --query-gpu=utilization.gpu,memory.used,memory.free --format=csv,noheader,nounits");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] memoryInfo = line.trim().split(",");
                porcentagem = Integer.parseInt(memoryInfo[0].trim());
                mem_uso = Long.parseLong(memoryInfo[1].trim());
                mem_disp = Long.parseLong(memoryInfo[2].trim());
                System.out.println("Porcentagem " + porcentagem + "%");
                System.out.println("Memória de Vídeo em Uso: " + mem_uso + " MiB");
                System.out.println("Memória de Vídeo Disponível: " + mem_disp + " MiB");
            }

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        Gpu gpu = new Gpu();
        List<GraphicsCard> listaGPU = gpu.getListaGPU();
        for (int i = 0; i < listaGPU.size(); i++) {
            if (listaGPU.get(i).getVendor().contains("NVIDIA")){
                System.out.println("TEM");
            }
        }
        Processador cpu = new Processador();
        System.out.println(cpu.getUso());
    }
}