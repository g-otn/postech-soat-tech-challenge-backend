package br.com.grupo63.techchallenge.core.application.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRepository<T> {

    @Query("SELECT obj FROM #{#entityName} obj WHERE obj.deleted = false")
    List<T> findByDeletedFalse();

    @Query("UPDATE #{#entityName} obj SET obj.deleted = true WHERE obj.id = :id")
    void removeLogicallyById(@Param("id") Long id);

}
