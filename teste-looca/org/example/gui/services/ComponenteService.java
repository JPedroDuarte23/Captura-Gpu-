package org.example.gui.services;

import org.example.gui.components.Aplicativo;
import org.example.gui.components.Componente;
import org.example.gui.components.Registro;
import org.example.gui.conexao.Conexao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ComponenteService {
    private CpuService cpuService;
    private DiscoService discoService;
    private GpuService gpuService;
    private MemoriaService memoriaService;
    private AplicativoService aplicativoService;
    private Integer fkUsuario;
    private Conexao conexao;
    private JdbcTemplate con;
    public ComponenteService(Integer fkUsuario){
        this.cpuService = new CpuService();
        this.discoService = new DiscoService();
        this.gpuService = new GpuService();
        this.memoriaService = new MemoriaService();
        this.aplicativoService = new AplicativoService();
        this.fkUsuario = fkUsuario;

        this.conexao = new Conexao();
        this.con = this.conexao.getConexaoDoBanco();
        con.execute("DROP TABLE IF EXISTS componente");
        con.execute("""
                CREATE TABLE componente (
                    id_componente INT PRIMARY KEY AUTO_INCREMENT,
                    fk_usuario INT,
                    nome VARCHAR(255),
                    tipo VARCHAR(20),
                    num_nucleo INT,
                    capacidade DOUBLE,
                    velocidade DOUBLE
                )""");
        con.execute("DROP TABLE IF EXISTS registro");
        con.execute("""
                CREATE TABLE registro (
                     id_registro INT PRIMARY KEY AUTO_INCREMENT,
                     fk_componente INT,
                     uso DOUBLE,
                     vel_grav DOUBLE,
                     vel_leit DOUBLE,
                     vel_cpu DOUBLE,
                     mem_uso DOUBLE,
                     mem_disp DOUBLE,
                     dt_hora DATETIME
                )""");
        con.execute("DROP TABLE IF EXISTS aplicativos_abertos");
        con.execute("""
                CREATE TABLE aplicativos_abertos(
                    id_aplicativo INT PRIMARY KEY AUTO_INCREMENT,
                    pid INT,
                    titulo varchar(50),
                    comando varchar(255),
                    usoCPU DOUBLE,
                    usoMemoria DOUBLE,
                    dt_hora DATETIME
                    )
                """);

    }
    public void cadastrarPecas(){
        cpuService.cadastrarProcessador(fkUsuario);
        discoService.cadastrarDisco(fkUsuario);
        gpuService.cadastrarGPU(fkUsuario);
        memoriaService.cadastrarMemoria(fkUsuario);
    }
    public void comecarCaptura(){
        Timer timer = new Timer();

        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                discoService.capturarDadosDisco();
                cpuService.capturarDadosProcessador();
                gpuService.capturarDadosGPU();
                memoriaService.capturarDadosMemoria();
                List<Registro> listaRegistro = con.query("SELECT * FROM registro", new BeanPropertyRowMapper<>(Registro.class));
                List<Aplicativo> listaAplicativo = con.query("SELECT * FROM aplicativos_abertos", new BeanPropertyRowMapper<>(Aplicativo.class));
                aplicativoService.capturarAplicativos();
                System.out.println(listaRegistro.toString());
                System.out.println(listaAplicativo.toString());

            }
        };
        timer.scheduleAtFixedRate(tarefa, 0, 1000);
    }
}
