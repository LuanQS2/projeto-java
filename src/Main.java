import controller.PedidoController;
import controller.ProdutoController;
import view.PedidoView;
import view.ProdutoView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ProdutoController produtoController = new ProdutoController();
        PedidoController pedidoController = new PedidoController(produtoController);

        ProdutoView produtoView = new ProdutoView(produtoController);
        PedidoView pedidoView = new PedidoView(pedidoController, produtoController);

        while (true) {
            String[] opcoes = {"Gerenciar Produtos", "Gerenciar Pedidos", "Sair"};
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma opção:",
                    "Menu Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            if (escolha == 0) {
                produtoView.exibirMenu();
            } else if (escolha == 1) {
                pedidoView.exibirMenu();
            } else if (escolha == 2 || escolha == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Encerrando o sistema. Até logo!");
                break;
            }
        }
    }
}
