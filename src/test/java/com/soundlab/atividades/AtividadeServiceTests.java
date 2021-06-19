package com.soundlab.atividades;

import com.soundlab.atividades.core.Atividade;
import com.soundlab.atividades.core.UserAssociation;
import com.soundlab.atividades.core.dto.AtividadeRequestDTO;
import com.soundlab.atividades.core.dto.AtividadeResponseDTO;
import com.soundlab.atividades.exceptions.AtividadeNotFoundException;
import com.soundlab.atividades.exceptions.UserNotFoundException;
import com.soundlab.atividades.external.user.UserExternalService;
import com.soundlab.atividades.external.user.models.UserResponse;
import com.soundlab.atividades.repositories.AtividadeRepository;
import com.soundlab.atividades.services.AtividadeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AtividadeServiceTests {

    @InjectMocks
    private AtividadeService atividadeService;

    @Mock
    private ModelMapper mapper;

    @Mock
    private AtividadeRepository atividadeRepository;

    @Mock
    private UserExternalService userExternalService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenFindAllIncludingNested_returnsProperData () {
        List<Atividade> mockAtividadesList = new ArrayList<>();
        Atividade mockAtividade = new Atividade();
        List<UserAssociation> mockUserAssociations = new ArrayList<>();
        UserAssociation mockUserAssociation = new UserAssociation();
        mockUserAssociation.setUserId(1111L);
        mockUserAssociations.add(mockUserAssociation);
        mockAtividade.setUserAssociations(mockUserAssociations);
        mockAtividadesList.add(mockAtividade);

        Mockito.doReturn(mockAtividadesList)
            .when(atividadeRepository)
            .findAll();

        Mockito.doReturn(new AtividadeResponseDTO())
            .when(mapper)
            .map(Mockito.any(), Mockito.any());

        Mockito.doReturn(new UserResponse())
            .when(userExternalService)
            .fetchById(Mockito.any());

        List<AtividadeResponseDTO> response = atividadeService.findAllIncludingNested();

        assertEquals(response.size(), mockAtividadesList.size());
        assertEquals(response.get(0).getUsers().size(), mockUserAssociations.size());
    }

    @Test
    void whenFindByIdIncludingNested_returnsProperData () {
        Atividade mockAtividade = new Atividade();
        List<UserAssociation> mockUserAssociations = new ArrayList<>();
        UserAssociation mockUserAssociation = new UserAssociation();
        mockUserAssociation.setUserId(1111L);
        mockUserAssociations.add(mockUserAssociation);
        mockAtividade.setUserAssociations(mockUserAssociations);

        Mockito.doReturn(Optional.of(mockAtividade))
            .when(atividadeRepository)
            .findById(Mockito.any());

        Mockito.doReturn(new AtividadeResponseDTO())
            .when(mapper)
            .map(Mockito.any(), Mockito.any());

        Mockito.doReturn(new UserResponse())
            .when(userExternalService)
            .fetchById(Mockito.any());

        Optional<AtividadeResponseDTO> response = atividadeService.findByIdIncludingNested(1111L);

        assertEquals(response.get().getUsers().size(), mockUserAssociations.size());
    }

    @Test
    void whenSave_savingSucceeds() {
        Mockito.doReturn(new Atividade())
            .when(atividadeRepository)
            .save(Mockito.any());

        Atividade response = atividadeService.save(new AtividadeRequestDTO());

        assertNotNull(response);
    }

    @Test
    void whenSaveGrade_andUserExists_andActivityExists_savingSucceeds() {
        UserResponse mockUserResponse = new UserResponse();
        mockUserResponse.setId(11111L);

        Atividade mockAtividade = new Atividade();
        List<UserAssociation> mockUserAssociations = new ArrayList<>();
        UserAssociation mockUserAssociation = new UserAssociation();
        mockUserAssociation.setUserId(1111L);
        mockUserAssociations.add(mockUserAssociation);
        mockAtividade.setUserAssociations(mockUserAssociations);

        Mockito.doReturn(mockUserResponse)
            .when(userExternalService)
            .fetchById(Mockito.any());

        Mockito.doReturn(Optional.of(mockAtividade))
            .when(atividadeRepository)
            .findById(Mockito.any(), Mockito.any());

        assertDoesNotThrow(() -> atividadeService.saveGrade(1111L, 1111L, 1));
    }

    @Test
    void whenSaveGrade_andUserDoesntExist_savingThrowsException() {
        Mockito.doReturn(null)
            .when(userExternalService)
            .fetchById(Mockito.any());

        assertThrows(UserNotFoundException.class, () -> atividadeService.saveGrade(1111L, 1111L, 1));
    }

    @Test
    void whenSaveGrade_andActivityDoesntExist_savingThrowsException() {
        UserResponse mockUserResponse = new UserResponse();
        mockUserResponse.setId(11111L);

        Mockito.doReturn(mockUserResponse)
            .when(userExternalService)
            .fetchById(Mockito.any());

        Mockito.doReturn(Optional.empty())
            .when(atividadeRepository)
            .findById(Mockito.any(), Mockito.any());

        assertThrows(AtividadeNotFoundException.class, () -> atividadeService.saveGrade(1111L, 1111L, 1));
    }

    @Test
    void whenUpdate_andActivityExists_updatingSucceeds() {
        Atividade mockAtividade = new Atividade();
        mockAtividade.setId(2222L);
        List<UserAssociation> mockUserAssociations = new ArrayList<>();
        UserAssociation mockUserAssociation = new UserAssociation();
        mockUserAssociation.setUserId(1111L);
        mockUserAssociations.add(mockUserAssociation);
        mockAtividade.setUserAssociations(mockUserAssociations);

        Mockito.doReturn(Optional.of(mockAtividade))
            .when(atividadeRepository)
            .findById(Mockito.any(), Mockito.any());

        Mockito.doReturn(new Atividade())
            .when(mapper)
            .map(Mockito.any(), Mockito.any());

        Mockito.doReturn(new Atividade())
            .when(atividadeRepository)
            .save(Mockito.any());

        assertDoesNotThrow(() -> atividadeService.update(1111L, new AtividadeRequestDTO()));
    }

    @Test
    void whenUpdate_andActivityDoesntExist_updatingThrowsException() {
        Mockito.doReturn(Optional.empty())
            .when(atividadeRepository)
            .findById(Mockito.any(), Mockito.any());

        assertThrows(AtividadeNotFoundException.class, () -> atividadeService.update(1111L, new AtividadeRequestDTO()));
    }
}
