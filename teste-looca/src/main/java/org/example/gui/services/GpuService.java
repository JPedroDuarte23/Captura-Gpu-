package org.example.gui.services;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.hardware.GraphicsCard;

import java.util.ArrayList;
import java.util.List;

public class GpuService {
    private List<GraphicsCard> listaGPUs;
    private List<String> listaRegistros;
    private org.example.gui.components.Componente componente;
    private String registro;
    private org.example.gui.conexao.Conexao conexao;
    private JdbcTemplate con;
    public GpuService() {
        this.listaGPUs = new ArrayList();
        this.listaRegistros = new ArrayList();
        this.componente = new org.example.gui.components.Componente();
        this.conexao = new org.example.gui.conexao.Conexao();
        this.con = this.conexao.getConexaoDoBanco();
    }
    public void cadastrarGPU(Integer fk){
        List<org.example.gui.components.Componente> dadosGPU;
        dadosGPU = con.query("SELECT * FROM componentes WHERE fk = '%d' AND tipo = 'GPU'".formatted(fk), new BeanPropertyRowMapper<>(org.example.gui.components.Componente.class));
        Gpu gpu = new org.example.gui.components.Gpu();
        listaGPUs = gpu.getListaGPU();
        if(this.listaGPUs.size() != dadosGPU.size()) {
            for (int i = 0; i < listaGPUs.size(); i++) {
                componente.setTipo("GPU");
                componente.setNome(listaGPUs.get(i).getName());
                componente.setCapacidade(listaGPUs.get(i).getVRam() / Math.pow(10, 9));
                componente.setNum_nucleo(null);
                componente.setVelocidade(null);
                con.update("INSERT INTO componente (nome, tipo, num_nucleo, capacidade, velocidade) VALUES ('%s', '%s', NULL, %.2f, NULL)".formatted(componente.getNome(),componente.getTipo(), componente.getCapacidade()));
            }
        }
    }
}
