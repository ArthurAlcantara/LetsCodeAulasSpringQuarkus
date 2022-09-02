package br.com.bb.letscode.idempotencia.controllers;

import br.com.bb.letscode.idempotencia.exceptions.EntidadeNaoEncontradaException;
import br.com.bb.letscode.idempotencia.model.Produto;
import br.com.bb.letscode.idempotencia.model.ResponseModel;
import br.com.bb.letscode.idempotencia.service.ProdutoService;
import br.com.bb.letscode.idempotencia.service.impl.ProdutoServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoServiceImpl produtoServiceClient;
    private static String  SUCESS_MESSAGE = "sucesso";

    @GetMapping
    public ResponseEntity<ResponseModel<List<Produto>>>  getTodos() {
        return ResponseEntity.ok(new ResponseModel<List<Produto>>(SUCESS_MESSAGE,produtoServiceClient.buscarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<Produto>> getId(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseModel<Produto>(SUCESS_MESSAGE,produtoServiceClient.buscaPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseModel<Produto>> salvar(@RequestBody @Valid Produto produto) {
        return ResponseEntity.ok(new ResponseModel<Produto>(SUCESS_MESSAGE,produtoServiceClient.salvar(produto)));
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<ResponseModel<String>> remover(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel<String>(SUCESS_MESSAGE,produtoServiceClient.removerPorId(id)));
        }catch(EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel<String>(e.getMessage(), null));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseModel<String>(e.getMessage(), null));
        }
        
    }

}
