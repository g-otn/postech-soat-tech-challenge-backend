package br.com.grupo63.techchallenge.gateway.client;

import br.com.grupo63.techchallenge.gateway.client.entity.ClientPersistenceEntity;
import br.com.grupo63.techchallenge.gateway.repository.IJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientJpaRepository extends JpaRepository<ClientPersistenceEntity, Long>, IJpaRepository<ClientPersistenceEntity> {

    Optional<ClientPersistenceEntity> findByNationalIdAndDeletedFalse(String nationalId);

}
