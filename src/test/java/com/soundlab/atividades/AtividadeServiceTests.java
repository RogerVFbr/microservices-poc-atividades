package com.soundlab.atividades;

import com.soundlab.atividades.external.user.UserExternalService;
import com.soundlab.atividades.services.AtividadeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtividadeServiceTests {

    @InjectMocks
    private AtividadeService atividadeService;

    @Mock
    private ModelMapper mapper;

    @Mock
    private UserExternalService userExternalService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void test () {
        assertEquals(1, 1);
    }
}
