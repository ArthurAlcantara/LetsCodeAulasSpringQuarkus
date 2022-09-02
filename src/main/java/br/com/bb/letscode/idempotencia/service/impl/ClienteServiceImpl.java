package br.com.bb.letscode.idempotencia.service.impl;

import br.com.bb.letscode.idempotencia.model.Cliente;
import br.com.bb.letscode.idempotencia.model.entity.ClienteEntity;
import br.com.bb.letscode.idempotencia.repository.ClienteRepository;
import br.com.bb.letscode.idempotencia.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public void salvarCliente(Cliente cliente) {

        final ClienteEntity entity = ClienteEntity.builder()
                .email(cliente.getEmail())
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .build();
        clienteRepository.save(entity);
        cliente.setId(entity.getId());
    }

    @Override
    public List<Cliente> getClientes() {
        return convert(clienteRepository.findAll());
    }

    @Override
    public Cliente getPorId(Long id){
        return convert(clienteRepository.findById(id));
    }

    private Cliente convert(ClienteEntity entity) {
        return Cliente.builder()
                .id(entity.getId())
                .cpf(entity.getCpf())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .build();
    }

    private List<Cliente> convert(List<ClienteEntity> entities) {
        return entities.stream().map(this::convert)
                .collect(Collectors.toList());
    }
}
