package br.com.bb.letscode.idempotencia.service;

import br.com.bb.letscode.idempotencia.model.Produto;

import java.util.List;

public interface ProdutoService {
    Produto salvar(Produto novoProduto);

    List<Produto> salvarLista(List<Produto> novoProduto);

    Produto buscaPorId(Long id);

    boolean isEmpty();

    String removerPorId(Long id) throws Exception;

    List<Produto> buscarTodos();
}
