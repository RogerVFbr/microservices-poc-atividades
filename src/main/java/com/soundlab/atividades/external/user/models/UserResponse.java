package com.soundlab.atividades.external.user.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonPropertyOrder({ "id", "name" })
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserResponse {
    private Long id;
    private String name;
}
