package com.soundlab.atividades.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AtividadeRequestDTO {
    @JsonProperty("name")
    @Schema(description = "Nome da atividade.", required = true)
    private String name;
}
