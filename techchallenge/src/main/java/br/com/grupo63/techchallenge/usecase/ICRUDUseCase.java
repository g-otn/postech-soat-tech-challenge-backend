package br.com.grupo63.techchallenge.usecase;

import br.com.grupo63.techchallenge.gateway.IPersistenceEntityRepository;
import br.com.grupo63.techchallenge.usecase.exception.NotFoundException;

import java.util.List;

public interface ICRUDUseCase<T> {

    T create(T entity, IPersistenceEntityRepository gateway) throws NotFoundException;

    T read(Long id, IPersistenceEntityRepository gateway) throws NotFoundException;

    List<T> list(IPersistenceEntityRepository gateway);

    T update(T entity, IPersistenceEntityRepository gateway) throws NotFoundException;

    void delete(Long id, IPersistenceEntityRepository gateway) throws NotFoundException;

}
