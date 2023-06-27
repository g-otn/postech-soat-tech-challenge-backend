package br.com.grupo63.techchallenge.core.application.repository;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRepository<T> {

    @Query("SELECT obj FROM #{#entityName} obj WHERE obj.deleted = false")
    List<T> findByDeletedFalse();

}
