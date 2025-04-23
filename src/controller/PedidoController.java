package controller;

import model.Pedido;
import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class PedidoController {
    private List<Pedido> listaPedidos = new ArrayList<>();
    private ProdutoController produtoController;

    public PedidoController(ProdutoController produtoController) {
        this.produtoController = produtoController;
    }

    public void adicionarPedido(Pedido pedido) {
        listaPedidos.add(pedido);
    }

    public List<Pedido> listarPedidos() {
        return listaPedidos;
    }

    public Pedido buscarPorId(int id) {
        for (Pedido p : listaPedidos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public boolean alterarPedido(int id, Pedido novoPedido) {
        for (int i = 0; i < listaPedidos.size(); i++) {
            if (listaPedidos.get(i).getId() == id) {
                listaPedidos.set(i, novoPedido);
                return true;
            }
        }
        return false;
    }

    public boolean excluirPedido(int id) {
        return listaPedidos.removeIf(p -> p.getId() == id);
    }
}
