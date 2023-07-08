package br.com.grupo63.techchallenge.core.application.usecase;

import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;

import java.util.List;

public interface ICRUDUseCase<T> {

    T create(T entity) throws NotFoundException;

    T read(Long id) throws NotFoundException;

    List<T> list();

    T update(T entity, Long id) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

}
