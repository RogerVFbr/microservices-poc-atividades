package com.soundlab.atividades.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AvaliacaoRequestDTO {
    @JsonProperty("activityId")
    @Schema(description = "ID da atividade.", required = true)
    private Long activityId;
    @JsonProperty("userId")
    @Schema(description = "ID de usuário.", required = true)
    private Long userId;
}
