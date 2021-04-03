package com.soundlab.atividades.services;

import com.soundlab.atividades.core.dto.AvaliacaoRequestDTO;
import com.soundlab.atividades.external.avaliacao.AvaliacaoExternalService;

import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {
    private AvaliacaoExternalService avaliacaoExternalService;

    public AvaliacaoService(AvaliacaoExternalService avaliacaoExternalService) {
        this.avaliacaoExternalService = avaliacaoExternalService;
    }

    public void postNewAvaliacao(AvaliacaoRequestDTO data) {
        avaliacaoExternalService.publish(data);
    }
}
