package com.soundlab.atividades.repositories;

import com.soundlab.atividades.core.Atividade;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    <T> List<T> findBy(Class<T> type);
    <T> Optional<T> findById(Long id, Class<T> type);
}
