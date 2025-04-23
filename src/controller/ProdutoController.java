package controller;

import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {
    private List<Produto> listaProduto = new ArrayList<>();

    public void adicionarProdutos(Produto produto) {
        listaProduto.add(produto);
    }

    public List<Produto> listarProdutos() {
        return listaProduto;
    }

    public Produto buscarPorId(int id) {
        for (Produto p : listaProduto) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public boolean alterarProduto(int id, Produto novoProduto) {
        for(int i = 0; i < listaProduto.size(); i++){
            if(listaProduto.get(i).getId() == id){
                listaProduto.set(i, novoProduto);
                return true;
            }
        }
        return false;
    }

    public boolean excluirProduto(int id) {
        return listaProduto.removeIf(p -> p.getId() == id);
    }

}
