package view;

import controller.PedidoController;
import controller.ProdutoController;
import model.ItemPedido;
import model.Pedido;
import model.Produto;

import javax.swing.*;

public class PedidoView {
    private PedidoController pedidoController;
    private ProdutoController produtoController;

    public PedidoView(PedidoController pedidoController, ProdutoController produtoController) {
        this.pedidoController = pedidoController;
        this.produtoController = produtoController;
        exibirMenu() ;
    }

    public void exibirMenu() {
        String[] opcoes = {"Inserir", "Listar", "Pesquisar por ID", "Alterar", "Excluir", "Voltar"};
        int opcao;
        do {
            opcao = JOptionPane.showOptionDialog(null, "Menu Pedidos", "Pedidos",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

            switch (opcao) {
                case 0 -> inserirPedido();
                case 1 -> listarPedidos();
                case 2 -> buscarPorId();
                case 3 -> alterarPedido();
                case 4 -> excluirPedido();
            }
        } while (opcao != 5);
    }

    private void inserirPedido() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID do Pedido:"));
            String descricao = JOptionPane.showInputDialog("Descrição do Pedido:");
            Pedido pedido = new Pedido(id, descricao);

            while (true) {
                int produtoId = Integer.parseInt(JOptionPane.showInputDialog("ID do Produto (ou -1 para sair):"));
                if (produtoId == -1) break;

                Produto produto = produtoController.buscarPorId(produtoId);
                if (produto == null) {
                    JOptionPane.showMessageDialog(null, "Produto não encontrado.");
                    continue;
                }

                int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade:"));
                pedido.adicionarItem(new ItemPedido(produto, quantidade));
            }

            pedidoController.adicionarPedido(pedido);
            JOptionPane.showMessageDialog(null, "Pedido criado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar pedido.");
        }
    }

    private void listarPedidos() {
        StringBuilder sb = new StringBuilder("Pedidos cadastrados:\n\n");
        for (Pedido p : pedidoController.listarPedidos()) {
            sb.append(p).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void buscarPorId() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID do Pedido:"));
            Pedido pedido = pedidoController.buscarPorId(id);
            if (pedido != null) {
                JOptionPane.showMessageDialog(null, pedido.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Pedido não encontrado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na busca.");
        }
    }

    private void alterarPedido() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID do Pedido para alterar:"));
            Pedido original = pedidoController.buscarPorId(id);
            if (original != null) {
                String descricao = JOptionPane.showInputDialog("Nova descrição:", original.getDescricao());
                Pedido novoPedido = new Pedido(id, descricao);

                while (true) {
                    int produtoId = Integer.parseInt(JOptionPane.showInputDialog("ID do Produto (ou -1 para sair):"));
                    if (produtoId == -1) break;

                    Produto produto = produtoController.buscarPorId(produtoId);
                    if (produto == null) {
                        JOptionPane.showMessageDialog(null, "Produto não encontrado.");
                        continue;
                    }

                    int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade:"));
                    novoPedido.adicionarItem(new ItemPedido(produto, quantidade));
                }

                pedidoController.alterarPedido(id, novoPedido);
                JOptionPane.showMessageDialog(null, "Pedido alterado com sucesso!");

            } else {
                JOptionPane.showMessageDialog(null, "Pedido não encontrado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar pedido.");
        }
    }

    private void excluirPedido() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID do Pedido a excluir:"));
            boolean sucesso = pedidoController.excluirPedido(id);
            JOptionPane.showMessageDialog(null, sucesso ? "Pedido excluído com sucesso!" : "Pedido não encontrado.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir pedido.");
        }
    }
}
