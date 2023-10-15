package org.example.gui.components;

public class Registro {
    private Integer id_registro;

    private Integer fk_componente;
    private Double uso;
    private Double vel_cpu;
    private Double vel_grav;
    private Double vel_leit;
    private Double mem_uso;
    private Double mem_disp;
    private String dt_hora;

    public Registro(Integer id_registro, Integer fk_componente, Double uso, Double vel_grav, Double vel_leit, Double vel_cpu,Double mem_uso, Double mem_disp, String dt_hora) {
        this.id_registro = id_registro;
        this.fk_componente = fk_componente;
        this.uso = uso;
        this.vel_grav = vel_grav;
        this.vel_leit = vel_leit;
        this.vel_cpu = vel_cpu;
        this.mem_uso = mem_uso;
        this.mem_disp = mem_disp;
        this.dt_hora = dt_hora;
    }
    public Registro(){}

    public Integer getId_registro() {
        return id_registro;
    }

    public void setId_registro(Integer id_registro) {
        this.id_registro = id_registro;
    }

    public Integer getFk_componente() {
        return fk_componente;
    }

    public void setFk_componente(Integer fk_componente) {
        this.fk_componente = fk_componente;
    }

    public Double getUso() {
        return uso;
    }

    public void setUso(Double uso) {
        this.uso = uso;
    }

    public Double getVel_grav() {
        return vel_grav;
    }

    public void setVel_grav(Double vel_grav) {
        this.vel_grav = vel_grav;
    }

    public Double getVel_leit() {
        return vel_leit;
    }

    public void setVel_leit(Double vel_leit) {
        this.vel_leit = vel_leit;
    }

    public Double getVel_cpu() {
        return vel_cpu;
    }

    public void setVel_cpu(Double vel_cpu) {
        this.vel_cpu = vel_cpu;
    }

    public Double getMem_uso() {
        return mem_uso;
    }

    public void setMem_uso(Double mem_uso) {
        this.mem_uso = mem_uso;
    }

    public Double getMem_disp() {
        return mem_disp;
    }

    public void setMem_disp(Double mem_disp) {
        this.mem_disp = mem_disp;
    }

    public String getDt_hora() {
        return dt_hora;
    }

    public void setDt_hora(String dt_hora) {
        this.dt_hora = dt_hora;
    }

    @Override
    public String toString() {
        return "\nRegistro{" +
                "id_registro=" + id_registro +
                ", fk_componente=" + fk_componente +
                ", uso=" + uso +
                ", vel_grav=" + vel_grav +
                ", vel_leitura=" + vel_leit +
                ", vel_cpu=" + vel_cpu +
                ", mem_uso=" + mem_uso +
                ", mem_disp=" + mem_disp +
                ", dt_hora='" + dt_hora + '\'' +
                '}';
    }
}
