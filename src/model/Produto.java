package model;

public class Produto {

    private int id;
    private String tipo;
    private String descricao;
    private double preco;

    public Produto(int id, String tipo, String descricao, double preco) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Tipo: " + tipo + ", Descrição: " + descricao + ", Preço: R$ " + preco;
    }
}
