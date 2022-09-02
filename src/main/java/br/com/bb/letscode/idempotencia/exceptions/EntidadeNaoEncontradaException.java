package br.com.bb.letscode.idempotencia.exceptions;

public class EntidadeNaoEncontradaException extends Exception{
    public EntidadeNaoEncontradaException(String msg){
        super(msg);
    }
}
