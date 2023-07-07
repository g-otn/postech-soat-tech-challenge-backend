package br.com.grupo63.techchallenge.adapter.out.repository.client.entity;

import br.com.grupo63.techchallenge.adapter.out.repository.DomainEntity;
import br.com.grupo63.techchallenge.core.domain.model.client.Client;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "cli_client", indexes = {})
public class ClientEntity extends DomainEntity {

    @Column(name = "national_id", length = 11)
    private String nationalId;

    public ClientEntity(Client client) {
        super(client);
        this.nationalId = client.getNationalId();
    }

    public Client toModel() {
        return new Client(this.getId(), this.isDeleted(), this.getNationalId());
    }
}
