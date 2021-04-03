package com.soundlab.atividades.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonPropertyOrder({ "id", "name", "grade" })
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserResponseDTO {
    private Long id;
    private String name;
    private Integer grade;
}
