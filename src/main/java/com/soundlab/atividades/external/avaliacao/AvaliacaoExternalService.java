package com.soundlab.atividades.external.avaliacao;

import com.amazonaws.services.sns.AmazonSNS;
import com.soundlab.atividades.core.dto.AvaliacaoRequestDTO;
import com.soundlab.atividades.external.AbstractExternalSnsService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoExternalService extends AbstractExternalSnsService<AvaliacaoRequestDTO> {
    public AvaliacaoExternalService(AmazonSNS client,
                                    @Value("${external.avaliacao.aws.arn}") String avaliacaoTopicArn) {
        super(client, avaliacaoTopicArn);
    }
}
