package com.soundlab.atividades.exceptions;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;

public class StreamLambdaHandlerException extends RuntimeException{
    public StreamLambdaHandlerException(ContainerInitializationException e) {
        super(String.format("Could not initialize Spring Boot application -> %s", e.getMessage()));
    }
}
