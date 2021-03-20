package com.soundlab.atividades.core.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.soundlab.atividades.external.user.models.UserResponse;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonPropertyOrder({ "id", "name" })
@Data
@NoArgsConstructor
public class AtividadeResponseDTO {
    private Long id;
    private String name;
    private List<UserResponse> users;

    public AtividadeResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponse> users) {
        this.users = users;
    }

    public AtividadeResponseDTO withUsers(List<UserResponse> users) {
        this.users = users;
        return this;
    }
}
