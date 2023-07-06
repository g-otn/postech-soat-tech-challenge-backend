package br.com.grupo63.techchallenge.core.application.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRepository<T> {

    List<T> findByDeletedFalse();

}
