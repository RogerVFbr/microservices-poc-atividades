package com.soundlab.atividades.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Tag(
    name = "Infra",
    description = "Endpoints de checagem da infraestrutura."
)
@RequestMapping("ping")
public class PingController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(OK)
    public String ping() {
        return "Endpoint pinged.";
    }
}
