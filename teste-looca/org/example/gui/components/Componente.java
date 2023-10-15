package org.example.gui.components;

public class Componente {
    private Integer id_componente;
    private Integer fk_usuario;
    private String tipo;
    private String nome;
    private Integer num_nucleo;
    private Double capacidade;
    private Double velocidade;

    public Componente(Integer id_componente,Integer fk_usuario,String tipo, String nome, Integer num_nucleo, Double capacidade, Double velocidade) {
        this.id_componente = id_componente;
        this.fk_usuario = fk_usuario;
        this.tipo = tipo;
        this.nome = nome;
        this.num_nucleo = num_nucleo;
        this.capacidade = capacidade;
        this.velocidade = velocidade;
    }

    public Componente() {}

    public Integer getId_componente() {
        return id_componente;
    }

    public void setId_componente(Integer id_componente) {
        this.id_componente = id_componente;
    }

    public Integer getFk_usuario() {
        return fk_usuario;
    }

    public void setFk_usuario(Integer fk_usuario) {
        this.fk_usuario = fk_usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNum_nucleo() {
        return num_nucleo;
    }

    public void setNum_nucleo(Integer num_nucleo) {
        this.num_nucleo = num_nucleo;
    }

    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }

    public Double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(Double velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public String toString() {
        return """ 
                Componente -
                id_componente: %d
                tipo: %s
                nome: %s
                num_nucleo:  %d
                capacidade:  %s
                velocidade: %s
                """.formatted(id_componente, tipo, nome, num_nucleo, capacidade, velocidade);
    }
}
