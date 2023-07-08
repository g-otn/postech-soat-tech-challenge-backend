package br.com.grupo63.techchallenge.adapter.out.repository;

import br.com.grupo63.techchallenge.adapter.out.repository.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IJpaRepository<E> {

    Optional<E> findByIdAndDeletedFalse(Long id);

    List<E> findByDeletedFalse();


}
