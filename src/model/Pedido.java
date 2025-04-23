package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int id;
    private List<ItemPedido> itens = new ArrayList<>();
    private String descricao;

    public Pedido(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    public int getId() { return id; }
    public List<ItemPedido> getItens() { return itens; }
    public String getDescricao() { return descricao; }

    public int getTotalItens() {
        return itens.stream().mapToInt(ItemPedido::getQuantidade).sum();
    }

    public double getPrecoTotal() {
        return itens.stream().mapToDouble(ItemPedido::getTotalItem).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pedido ID: " + id + "\nDescrição: " + descricao + "\nItens:\n");
        for (ItemPedido item : itens) {
            sb.append("- ").append(item).append("\n");
        }
        sb.append("Total de Itens: ").append(getTotalItens()).append("\n");
        sb.append("Preço Total: R$ ").append(getPrecoTotal()).append("\n");
        return sb.toString();
    }
}
