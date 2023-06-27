package br.com.grupo63.techchallenge.core.application.usecase;

import br.com.grupo63.techchallenge.core.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ICRUDUseCase<T> {

    T create(T entity);

    Optional<T> read();

    List<T> list();

    T update();

    void delete();

}
