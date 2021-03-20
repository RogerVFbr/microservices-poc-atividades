package com.soundlab.atividades.exceptions;

public class AtividadeNotFoundException extends RuntimeException{
    public AtividadeNotFoundException(Long id) {
        super(String.format("Atividade com id '%s' não encontrada.", id));
    }
}
