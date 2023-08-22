package br.com.grupo63.techchallenge.gateway;

import java.util.List;
import java.util.Optional;

public interface IGateway<T> {

    List<T> findByDeletedFalse();

    T saveAndFlush(T entity);

    Optional<T> findByIdAndDeletedFalse(Long id);
}
