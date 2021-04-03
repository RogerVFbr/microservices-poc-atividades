package com.soundlab.atividades.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super(String.format("Usuário com id '%s' não encontrado.", id));
    }
}
