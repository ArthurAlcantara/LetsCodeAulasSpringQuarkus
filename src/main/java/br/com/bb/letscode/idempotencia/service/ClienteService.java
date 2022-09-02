package br.com.bb.letscode.idempotencia.service;


import br.com.bb.letscode.idempotencia.model.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente getPorId(Long id);
    void salvarCliente(Cliente cliente);
    List<Cliente> getClientes();
}
