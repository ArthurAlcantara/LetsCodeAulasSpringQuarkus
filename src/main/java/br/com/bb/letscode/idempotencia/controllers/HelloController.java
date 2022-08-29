package br.com.bb.letscode.idempotencia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import model.ResponseModel;
import br.com.bb.letscode.idempotencia.services.HorarioService;

@RestController
@RequestMapping("hello")
@Slf4j
// @RequiredArgsConstructor
public class HelloController {

    @Autowired
    HorarioService hrService;

    @GetMapping
    public ResponseEntity<ResponseModel<String>> greetingGenerico() {
        log.info("Chamada do path /hello -> Diga oi");
        ResponseModel<String> rm = new ResponseModel<String>("Sucesso", "Olá Pessoa!");
        return ResponseEntity.status(HttpStatus.OK).body(rm);
    }

    @GetMapping("{nmPessoa}")
    public ResponseEntity<ResponseModel<String>> greetingPorNome(@PathVariable String nmPessoa) {
        log.info("Chamada do path /hello/" + nmPessoa + " -> Diga oi para " + nmPessoa);
        ResponseModel<String> rm = new ResponseModel<String>("Sucesso", "Olá " + nmPessoa + ", seja bem vindo.");
        return ResponseEntity.status(HttpStatus.OK).body(rm);
    }

    @GetMapping("{nmPessoa}/horario")
    public ResponseEntity<ResponseModel<String>> greetingEHorarioPorNome(@PathVariable String nmPessoa) {
        log.info("Chamada do path /hello/" + nmPessoa + "/horario -> Diga oi e a hora para " + nmPessoa);
        ResponseModel<String> rm = new ResponseModel<String>("Sucesso",
                "Olá " + nmPessoa + ", seja bem vindo. Agora são " + hrService.now() + ", não se esqueça!");
        return ResponseEntity.status(HttpStatus.OK).body(rm);
    }
}
