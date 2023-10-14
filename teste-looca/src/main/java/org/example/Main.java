package src.main.java.org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.example.gui.components.Gpu;
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
        try {
            Process process = Runtime.getRuntime().exec("nvidia-smi --query-gpu=utilization.gpu --format=csv,noheader,nounits");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            Process process2 = Runtime.getRuntime().exec("nvidia-smi --query-gpu=memory.total --format=csv,noheader,nounits");
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(process2.getInputStream()));

            String line;
            String line2;
            while ((line = reader.readLine()) != null) {
                int utilization = Integer.parseInt(line.trim());
                System.out.println("Uso da GPU: " + utilization + "%");
            }
            while ((line2 = reader2.readLine()) != null) {
                long memory =  Long.parseLong(line2.trim());;
                System.out.println("Memoria da GPU: " + memory + "MiB");
            }

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        Gpu gpu = new Gpu();
    }
}