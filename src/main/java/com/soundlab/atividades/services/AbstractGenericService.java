package com.soundlab.atividades.services;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractGenericService<T extends JpaRepository<Z, Long>, Z>{
    T repository;

    protected AbstractGenericService(T repository) {
        this.repository = repository;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
