package com.soundlab.atividades.services;

import com.soundlab.atividades.core.Atividade;
import com.soundlab.atividades.core.UserAssociation;
import com.soundlab.atividades.core.dto.AtividadeRequestDTO;
import com.soundlab.atividades.core.dto.AtividadeResponseDTO;
import com.soundlab.atividades.core.dto.UserResponseDTO;
import com.soundlab.atividades.exceptions.AtividadeNotFoundException;
import com.soundlab.atividades.exceptions.UserNotFoundException;
import com.soundlab.atividades.external.user.UserExternalService;
import com.soundlab.atividades.external.user.models.UserResponse;
import com.soundlab.atividades.repositories.AtividadeRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtividadeService extends AbstractGenericService<AtividadeRepository, Atividade> {
    private final ModelMapper mapper;
    private final UserExternalService userExternalService;

    public AtividadeService(AtividadeRepository atividadeRepository,
                            UserExternalService userExternalService,
                            @Qualifier("genericMapper") ModelMapper mapper) {
        super(atividadeRepository);
        this.userExternalService = userExternalService;
        this.mapper = mapper;
    }

    public List<AtividadeResponseDTO> findAllIncludingNested() {
        Map<Long, UserResponse> externalUsers = new HashMap<>();
        return repository.findAll().stream()
            .map(atividade -> mapper.map(atividade, AtividadeResponseDTO.class)
                .withUsers(atividade.getUserAssociations().stream()
                    .map(association ->
                        mapResponseEntity(getExternalUserById(association.getUserId(), externalUsers), association))
                    .collect(Collectors.toList())
                ))
            .collect(Collectors.toList());
    }

    private UserResponse getExternalUserById(Long userId, Map<Long, UserResponse> externalUsers) {
        if (!externalUsers.containsKey(userId))
            externalUsers.put(userId, userExternalService.fetchById(userId));
        return externalUsers.get(userId);
    }

    public Optional<AtividadeResponseDTO> findByIdIncludingNested(Long id) {
        return repository.findById(id)
            .map(atividade -> mapper.map(atividade, AtividadeResponseDTO.class)
                .withUsers(atividade.getUserAssociations().stream()
                    .map(association ->
                        mapResponseEntity(userExternalService.fetchById(association.getUserId()), association))
                    .collect(Collectors.toList())
                ));
    }

    public Atividade save(AtividadeRequestDTO request) {
        return repository.save(new Atividade().withName(request.getName()));
    }

    public void saveGrade(Long activityId, Long userId, Integer grade) {
        if (userExternalService.fetchById(userId) == null) throw new UserNotFoundException(userId);

        Atividade atividade = repository
            .findById(activityId, Atividade.class)
            .orElseThrow(() -> new AtividadeNotFoundException(activityId));

        atividade.getUserAssociations().removeIf(association -> association.getUserId().equals(userId));

        atividade.getUserAssociations().add(
            new UserAssociation()
                .withActivityId(activityId)
                .withUserId(userId)
                .withGrade(grade));

        repository.save(atividade);
    }

    public void update(Long id, AtividadeRequestDTO data) {
        Atividade original = repository.findById(id, Atividade.class)
            .orElseThrow(() -> new AtividadeNotFoundException(id));
        repository.save(mapper.map(data, Atividade.class).withId(original.getId()));
    }

    private UserResponseDTO mapResponseEntity(UserResponse userResponse, UserAssociation association) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userResponse.getId());
        userResponseDTO.setName(userResponse.getName());
        userResponseDTO.setGrade(association.getGrade());
        return userResponseDTO;
    }
}
