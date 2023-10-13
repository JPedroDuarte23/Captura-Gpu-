package org.example.gui.components;

public class Componente {
    private Integer id;
    private String tipo;
    private String nome;
    private Integer num_nucleo;
    private Double capacidade;
    private Double velocidade;

    public Componente(Integer id, String tipo, String nome, Integer num_nucleo, Double capacidade, Double velocidade) {
        this.id = 0;
        this.tipo = tipo;
        this.nome = nome;
        this.num_nucleo = num_nucleo;
        this.capacidade = capacidade;
        this.velocidade = velocidade;
    }

    public Componente() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
                id: %d
                tipo: %s
                nome: %s
                num_nucleo:  %d
                capacidade:  %.2f 
                velocidade: %.2f
                """.formatted(tipo, nome, num_nucleo, capacidade, velocidade);
    }
}
