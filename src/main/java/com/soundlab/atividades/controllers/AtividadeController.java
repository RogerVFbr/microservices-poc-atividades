package com.soundlab.atividades.controllers;

import com.soundlab.atividades.core.Atividade;
import com.soundlab.atividades.core.dto.AtividadeRequestDTO;
import com.soundlab.atividades.core.dto.AtividadeResponseDTO;
import com.soundlab.atividades.exceptions.AtividadeNotFoundException;
import com.soundlab.atividades.services.AtividadeService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Tag(
    name = "Domain: Atividades",
    description = "Gerencia o domínio 'Atividades'."
)
@RequestMapping("atividades")
public class AtividadeController {
    private final AtividadeService atividadeService;

    public AtividadeController(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(OK)
    List<AtividadeResponseDTO> findAll() {
        return atividadeService.findAllIncludingNested();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseStatus(OK)
    AtividadeResponseDTO getById(@PathVariable long id) {
        return atividadeService
            .findByIdIncludingNested(id)
            .orElseThrow(() -> new AtividadeNotFoundException(id));
    }

    @RequestMapping(method = RequestMethod.POST, produces = { "application/json"})
    ResponseEntity<String> save(@RequestBody AtividadeRequestDTO data) {
        Atividade createdActivity = atividadeService.save(data);
        String location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdActivity.getId())
            .toUriString();
        return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, location).build();
    }

    @RequestMapping(method = RequestMethod.POST, params = { "idAtividade", "idUser", "grade" }, produces = { "application/json"})
    @ResponseStatus(CREATED)
    void saveGrade(@RequestParam("idAtividade") Long activityId,
                   @RequestParam("idUser") Long userId,
                   @RequestParam("grade") Integer grade) {
        atividadeService.saveGrade(activityId, userId, grade);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(NO_CONTENT)
    void update(@PathVariable Long id, @RequestBody AtividadeRequestDTO data) {
        atividadeService.update(id, data);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable long id) {
        atividadeService.delete(id);
    }
}
