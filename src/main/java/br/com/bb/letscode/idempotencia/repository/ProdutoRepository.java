package br.com.bb.letscode.idempotencia.repository;

import br.com.bb.letscode.idempotencia.model.entity.ProdutoEntity;
import br.com.bb.letscode.idempotencia.model.entity.ProdutoProjecao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends CrudRepository<ProdutoEntity, Long> {
    List<ProdutoProjecao> findByDescricao(String descricao);
    // select * from tb_produto p where p.descricao = :descricao
}
