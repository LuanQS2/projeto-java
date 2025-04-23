package view;

import controller.ProdutoController;
import model.Produto;

import javax.swing.*;

public class ProdutoView {

    private ProdutoController produtoController;

    public ProdutoView(ProdutoController controller) {
        this.produtoController = controller;
        exibirMenu();
    }

    public void exibirMenu() {
        String[] opcoes = {"Inserir", "Listar", "Pesquisar por ID", "Alterar", "Excluir", "Voltar"};
        int opcao;
        do {
            opcao = JOptionPane.showOptionDialog(null, "Menu Produtos", "Produtos",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

            switch (opcao) {
                case 0 -> inserirProduto();
                case 1 -> listarProdutos();
                case 2 -> buscarPorId();
                case 3 -> alterarProduto();
                case 4 -> excluirProduto();
            }
        } while (opcao != 5);
    }

    private void inserirProduto() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID:"));
            String tipo = JOptionPane.showInputDialog("Tipo:");
            String descricao = JOptionPane.showInputDialog("Descrição:");
            double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço:"));

            produtoController.adicionarProdutos(new Produto(id, tipo, descricao, preco));
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto.");
        }
    }

    private void listarProdutos() {
        StringBuilder sb = new StringBuilder("Produtos cadastrados:\n\n");
        for (Produto p : produtoController.listarProdutos()) {
            sb.append(p).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void buscarPorId() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID do Produto:"));
            Produto produto = produtoController.buscarPorId(id);
            JOptionPane.showMessageDialog(null, produto != null ? produto : "Produto não encontrado.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na busca.");
        }
    }

    private void alterarProduto() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID do Produto para alterar:"));
            Produto produtoAntigo = produtoController.buscarPorId(id);
            if (produtoAntigo != null) {
                String tipo = JOptionPane.showInputDialog("Novo Tipo:", produtoAntigo.getTipo());
                String descricao = JOptionPane.showInputDialog("Nova Descrição:", produtoAntigo.getDescricao());
                double preco = Double.parseDouble(JOptionPane.showInputDialog("Novo Preço:", produtoAntigo.getPreco()));
                Produto novoProduto = new Produto(id, tipo, descricao, preco);
                produtoController.alterarProduto(id, novoProduto);
                JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar produto.");
        }
    }

    private void excluirProduto() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID do Produto a excluir:"));
            boolean sucesso = produtoController.excluirProduto(id);
            JOptionPane.showMessageDialog(null, sucesso ? "Produto excluído com sucesso!" : "Produto não encontrado.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto.");
        }
    }
}
