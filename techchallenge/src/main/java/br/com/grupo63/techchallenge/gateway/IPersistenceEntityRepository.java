package br.com.grupo63.techchallenge.gateway;

import java.util.List;

public interface IPersistenceEntityRepository<T> {

    List<T> findByDeletedFalse();

    T saveAndFlush(T entity);

}
