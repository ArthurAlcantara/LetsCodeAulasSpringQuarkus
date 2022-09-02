package br.com.bb.letscode.idempotencia.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

import br.com.bb.letscode.idempotencia.model.Cliente;
import br.com.bb.letscode.idempotencia.model.ResponseModel;
import br.com.bb.letscode.idempotencia.service.ClienteService;
import br.com.bb.letscode.idempotencia.service.impl.ClienteServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("cliente")
@Slf4j
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteServiceImpl cliService;

    @GetMapping
    public ResponseEntity<ResponseModel<List<Cliente>>> testeGetTodos() {
        log.error("Mostrar Todos Os Clientes");
        ResponseModel<List<Cliente>> rm = new ResponseModel<List<Cliente>>("Sucesso",
                cliService.getClientes());
        return ResponseEntity.status(HttpStatus.OK).body(rm);
    }

    @GetMapping("{id}")
    public ResponseEntity<String> testeGetPorId(@PathVariable String id) {
        return ResponseEntity.ok("Cliente Id: " + id);
    }

    @GetMapping("buscar")
    public ResponseEntity<ResponseModel<Cliente>> testeGetPorIdPath(@RequestParam Long id) {
        Cliente cli = cliService.getPorId(id);
        if (Objects.isNull(cli)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseModel<Cliente>("Cliente não encontrado", null));
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseModel<Cliente>("Sucesso", cli));
        }
    }

    @PostMapping("salvar")
    public ResponseEntity<ResponseModel<Cliente>> salvarCliente(@RequestBody Cliente cli) {
        cliService.salvarCliente(cli);
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
        if (cli.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseModel<Cliente>("Id é obrigatório", null));
        }
        try{
            cliService.salvarCliente(cli);
            return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseModel<Cliente>("Cliente atualizado com sucesso", cli));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ResponseModel<Cliente>("Cliente não encontrado", null));
        }
    }

}
