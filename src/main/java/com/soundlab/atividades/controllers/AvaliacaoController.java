package com.soundlab.atividades.controllers;

import com.soundlab.atividades.core.dto.AvaliacaoRequestDTO;
import com.soundlab.atividades.services.AvaliacaoService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@Tag(
    name = "Domain: Avaliacao",
    description = "Gerencia o domínio 'Avaliacao'."
)
@RequestMapping("avaliacao")
public class AvaliacaoController {

    private AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @RequestMapping(method = RequestMethod.POST, produces = { "application/json"})
    @ResponseStatus(CREATED)
    void saveActivity(@RequestBody AvaliacaoRequestDTO data) {
        avaliacaoService.postNewAvaliacao(data);
    }
}
