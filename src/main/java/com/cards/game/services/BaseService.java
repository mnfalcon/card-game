package com.cards.game.services;

import com.cards.game.models.BaseEntity;
import com.cards.game.services.exceptions.NotFoundException;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T extends BaseEntity> {


    protected JpaRepository<T, Long> repository;
    protected String entityName;

    public BaseService(JpaRepository<T, Long> repository) {
        this.repository = repository;
        this.entityName = GenericTypeResolver.resolveTypeArgument(getClass(), BaseService.class).getSimpleName();
    }

    public T save(T card) {
        return repository.save(card);
    }

    public T findById(Long id) {
        Optional<T> obj = repository.findById(id);
        return obj.orElseThrow(()-> new NotFoundException(entityName, id));
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public Page getByPage(int page, int limit) {
        PageRequest r = PageRequest.of(page, limit);
        return repository.findAll(r);
    }

    public T update(T object, Long id) {
        if (repository.existsById(id)) {
            object.setId(id);
            repository.save(object);
        }
        throw new NotFoundException(entityName, id);
    }

}
