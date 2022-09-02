package br.com.bb.letscode.idempotencia.service.impl;

import br.com.bb.letscode.idempotencia.converter.ProdutoConverter;
import br.com.bb.letscode.idempotencia.exceptions.EntidadeNaoEncontradaException;
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
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;

    @Override
    public Produto salvar(Produto novoProduto) {
        final ProdutoEntity produtoEntity = ProdutoConverter.convertProductTo(novoProduto);
        final ProdutoEntity produtoSalvo = repository.save(produtoEntity);
        return ProdutoConverter.convertEntityTo(produtoSalvo);
    }

    @Override
    public List<Produto> salvarLista(List<Produto> novoProduto) {
        final List<ProdutoEntity> produtoEntityList = ProdutoConverter.convertProductTo(novoProduto);
        final List<ProdutoEntity> produtoEntities = new ArrayList<>();
        final Iterable<ProdutoEntity> iterable = repository.saveAll(produtoEntityList);
        iterable.forEach(produtoEntities::add);
        return ProdutoConverter.convertEntityTo(produtoEntities);
    }

    @Override
    public Produto buscaPorId(Long id) {
        final ProdutoEntity produtoSalvo = repository.findById(id)
                .orElseThrow(RuntimeException::new);
        return ProdutoConverter.convertEntityTo(produtoSalvo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isEmpty() {
        return repository.count() == 0;
    }

    @Override
    public String removerPorId(Long id) throws Exception{
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "Removido com sucesso";
        }else{
            throw new EntidadeNaoEncontradaException("Registro Não Encontrado");
        }
    }

    @Override
    public List<Produto> buscarTodos() {
        final List<ProdutoEntity> produtoEntities = new ArrayList<>();
        Iterable<ProdutoEntity> result =  repository.findAll();
        result.forEach(produtoEntities::add);
        return ProdutoConverter.convertEntityTo(produtoEntities);
    }


}
