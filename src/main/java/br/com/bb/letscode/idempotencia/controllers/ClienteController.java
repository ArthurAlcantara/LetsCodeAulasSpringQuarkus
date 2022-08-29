package br.com.bb.letscode.idempotencia.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import model.Cliente;
import model.ResponseModel;

@RestController
@RequestMapping("cliente")
@Slf4j
public class ClienteController {

    Map<String, Cliente> repo = new HashMap<String, Cliente>();

    @GetMapping
    public ResponseEntity<ResponseModel<List<Cliente>>> testeGetTodos() {
        log.error("Mostrar Todos Os Clientes");
        ResponseModel<List<Cliente>> rm = new ResponseModel<List<Cliente>>("Sucesso",
                repo.values().stream().collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.OK).body(rm);
    }

    @GetMapping("{id}")
    public ResponseEntity<String> testeGetPorId(@PathVariable String id) {
        return ResponseEntity.ok("Cliente Id: " + id);
    }

    @GetMapping("buscar")
    public ResponseEntity<ResponseModel<Cliente>> testeGetPorIdPath(@RequestParam String id) {
        log.info(id);
        if (!repo.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseModel<Cliente>("Cliente não encontrado", null));
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseModel<Cliente>("Sucesso", repo.get(id)));
        }
    }

    @PostMapping("salvar")
    public ResponseEntity<ResponseModel<Cliente>> salvarCliente(@RequestBody Cliente cli) {
        String id = UUID.randomUUID().toString();
        cli.setId(id);
        repo.put(id, cli);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseModel<Cliente>("Cliente criado com sucesso: ", cli));
    }

    @PostMapping
    public ResponseEntity<String> testePost() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("Aqui está seu número aleatório -> " + ThreadLocalRandom.current().nextInt(0, 100));
    }

    @PutMapping("atualizar")
    public ResponseEntity<ResponseModel<Cliente>> testePut(@RequestBody Cliente cli) {
        if (!repo.containsKey(cli.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseModel<Cliente>("Cliente não encontrado", null));
        } else {
            repo.put(cli.getId(), cli);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseModel<Cliente>("Cliente atualizado com sucesso", cli));
    }

}
