package br.com.bb.letscode.idempotencia.service.impl;

import br.com.bb.letscode.idempotencia.model.Cliente;
import br.com.bb.letscode.idempotencia.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class ClienteServiceInMemory implements ClienteService {

    private static Map<Long, Cliente> MAP = new HashMap<>();
    private static AtomicLong GERADOR_ID = new AtomicLong();

    public void salvarCliente(Cliente cliente) {
        cliente.setId(GERADOR_ID.incrementAndGet());
        MAP.put(cliente.getId(), cliente);
        log.debug("Cliente salvo: {}", cliente);
    }

    public List<Cliente> getClientes() {
        return new ArrayList<>(MAP.values());
    }

    @Override
    public Cliente getPorId(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
