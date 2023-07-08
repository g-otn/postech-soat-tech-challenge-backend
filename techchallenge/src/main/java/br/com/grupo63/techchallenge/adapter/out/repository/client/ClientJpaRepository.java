package br.com.grupo63.techchallenge.adapter.out.repository.client;

import br.com.grupo63.techchallenge.adapter.out.repository.IJpaRepository;
import br.com.grupo63.techchallenge.adapter.out.repository.client.entity.ClientEntity;
import br.com.grupo63.techchallenge.core.domain.model.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientJpaRepository extends JpaRepository<ClientEntity, Long>, IJpaRepository<ClientEntity> {

    Optional<ClientEntity> findByNationalIdAndDeletedFalse(String nationalId);

}
