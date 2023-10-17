package org.example.gui.services;


import org.example.gui.components.Componente;
import org.example.gui.components.Gpu;
import org.example.gui.conexao.Conexao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.hardware.GraphicsCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class GpuService {
    private List<GraphicsCard> listaGPUs;
    private List<Componente> dadosGPU;
    private List<Integer> listaIdComponente;
    private Componente componente;
    private Conexao conexao;
    private JdbcTemplate con;
    public GpuService() {
        this.listaGPUs = new ArrayList();
        this.listaIdComponente = new ArrayList();
        this.componente = new Componente();
        this.conexao = new Conexao();
        this.con = this.conexao.getConexaoDoBanco();
    }
    public void cadastrarGPU(Integer fk){
        DecimalFormat df = new DecimalFormat("0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(java.util.Locale.US));
        this.dadosGPU = con.query("SELECT * FROM componente WHERE fk_usuario = %d AND tipo = 'GPU'".formatted(fk), new BeanPropertyRowMapper<>(Componente.class));
        Gpu gpu = new Gpu();
        listaGPUs = gpu.getListaGPU();
        if(this.listaGPUs.size() != dadosGPU.size()) {
            for (int i = dadosGPU.size(); i < listaGPUs.size(); i++) {
                componente.setTipo("GPU");
                componente.setNome(listaGPUs.get(i).getName());
                componente.setCapacidade(listaGPUs.get(i).getVRam() / (1024.0 * 1024.0 * 1024.0));
                componente.setNum_nucleo(null);
                componente.setVelocidade(null);
                con.update("INSERT INTO componente (fk_usuario, nome, tipo, num_nucleo, capacidade, velocidade) VALUES (%d, '%s', '%s', NULL, %s, NULL)".formatted(fk, componente.getNome(),componente.getTipo(), df.format(componente.getCapacidade())));
            }
            this.dadosGPU = con.query("SELECT * FROM componente WHERE fk_usuario = %d AND tipo = 'GPU'".formatted(fk), new BeanPropertyRowMapper<>(Componente.class));
            System.out.println("""
                                   Inseri a gpu
                                   nome: %s
                                   fk_usuario: %d
                                   tipo: %s
                                   num_nucle0: null
                                   capacidade: %s
                                   velocidade: null
                                   """.formatted(componente.getNome(), fk, componente.getTipo(), df.format(componente.getCapacidade())));
        }
        for (int i = 0; i < dadosGPU.size(); i++) {
            this.listaIdComponente.add(dadosGPU.get(i).getId_componente());
        }
    }
    public void capturarDadosGPU(){
        DecimalFormat df = new DecimalFormat("0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(java.util.Locale.US));



        for (int i = 0; i < dadosGPU.size(); i++) {
            if (listaGPUs.get(i).getVendor().contains("NVIDIA")){
                int porcentagem = 0;
                Double mem_uso = 0.0;
                Double mem_disp = 0.0;
                Integer idGPU;
                idGPU = dadosGPU.get(i).getId_componente();
                try {
                    Process process = Runtime.getRuntime().exec("nvidia-smi --query-gpu=utilization.gpu,memory.used,memory.free --format=csv,noheader,nounits");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] memoryInfo = line.trim().split(",");
                        porcentagem = Integer.parseInt(memoryInfo[0].trim());
                        mem_uso = Long.parseLong(memoryInfo[1].trim()) / 1024.0;
                        mem_disp = Long.parseLong(memoryInfo[2].trim()) / 1024.0;
                    }

                    process.waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                con.update("INSERT INTO registro (fk_componente, uso, vel_grav, vel_leit, vel_cpu, mem_uso, mem_disp, dt_hora) VALUES (%d ,%s, null, null, null, %s, %s, now() )".formatted(idGPU, df.format(porcentagem), df.format(mem_uso), df.format(mem_disp)));
            }
        }
    }
}
