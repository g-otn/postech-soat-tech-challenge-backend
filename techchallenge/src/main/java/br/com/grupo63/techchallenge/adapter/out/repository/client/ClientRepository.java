package br.com.grupo63.techchallenge.adapter.out.repository.client;

import br.com.grupo63.techchallenge.adapter.out.repository.client.entity.ClientEntity;
import br.com.grupo63.techchallenge.core.application.repository.IClientRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long>, IClientRepository {

}
