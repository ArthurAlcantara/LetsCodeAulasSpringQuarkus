package br.com.bb.letscode.idempotencia.httpclient.impl;

import javax.validation.Valid;

import br.com.bb.letscode.idempotencia.httpclient.ProdutoQuarkusClient;
import br.com.bb.letscode.idempotencia.model.Produto;

public class ProdutoClient implements ProdutoQuarkusClient{

    @Override
    public Produto salvar(@Valid Produto produto) {
        return null;
    }

    @Override
    public Produto buscaPorId(Long id) {
        return null;
    }
    
}
