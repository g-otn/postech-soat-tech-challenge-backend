package br.com.grupo63.techchallenge.adapter.out.infrastructure;

import br.com.grupo63.techchallenge.core.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
