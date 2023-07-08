package br.com.grupo63.techchallenge.core.application.repository;

import java.util.List;

public interface IRepository<T> {

    List<T> findByDeletedFalse();

    T saveAndFlush(T entity);

}
