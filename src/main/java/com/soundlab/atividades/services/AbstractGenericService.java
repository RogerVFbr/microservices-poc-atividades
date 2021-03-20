package com.soundlab.atividades.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractGenericService<T extends JpaRepository<Z, Long>, Z>{
    T repository;

    protected AbstractGenericService(T repository) {
        this.repository = repository;
    }

    public List<Z> findAll() {
        return repository.findAll();
    }

    public Z findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Z save(Z obj) {
        return repository.save(obj);
    }

    public Z update(Z obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
