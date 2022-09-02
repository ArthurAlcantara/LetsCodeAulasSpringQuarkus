package br.com.bb.letscode.idempotencia.service.impl;

import br.com.bb.letscode.idempotencia.httpclient.ProdutoQuarkusClient;
import br.com.bb.letscode.idempotencia.httpclient.impl.ProdutoClient;
import br.com.bb.letscode.idempotencia.converter.ProdutoConverter;
import br.com.bb.letscode.idempotencia.model.Produto;
import br.com.bb.letscode.idempotencia.model.entity.ProdutoEntity;
import br.com.bb.letscode.idempotencia.repository.ProdutoRepository;
import br.com.bb.letscode.idempotencia.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class ProdutoServiceClient implements ProdutoService {

    private final ProdutoClient quarkusClient = new ProdutoClient();

    @Override
    public Produto salvar(Produto novoProduto) {
        return quarkusClient.salvar(novoProduto);
    }

    @Override
    public List<Produto> salvarLista(List<Produto> novoProduto) {
//        final List<ProdutoEntity> produtoEntityList = ProdutoConverter.convertProductTo(novoProduto);
//        final List<ProdutoEntity> produtoEntities = new ArrayList<>();
//        final Iterable<ProdutoEntity> iterable = quarkusClient.saveAll(produtoEntityList);
//        iterable.forEach(produtoEntities::add);
//        return ProdutoConverter.convertEntityTo(produtoEntities);
        return null;
    }

    @Override
    public Produto buscaPorId(Long id) {
        return quarkusClient.buscaPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isEmpty() {
        return false;// quarkusClient.count() == 0;
    }

    @Override
    public String removerPorId(Long id) {
        // TODO Auto-generated method stub
        return "OK";
    }

    @Override
    public List<Produto> buscarTodos() {
        // TODO Auto-generated method stub
        return null;
    }


}
