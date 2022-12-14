package br.com.bb.letscode.idempotencia.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseModel<T> {
    private String mensagem;
    private T payload;
}
