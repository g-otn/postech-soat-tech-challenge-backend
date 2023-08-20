package br.com.grupo63.techchallenge.gateway.client.entity;

import br.com.grupo63.techchallenge.gateway.PersistenceEntity;
import br.com.grupo63.techchallenge.entity.client.Client;
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
public class ClientPersistenceEntity extends PersistenceEntity {

    @Column(name = "national_id", length = 11)
    private String nationalId;

    public ClientPersistenceEntity(Client client) {
        super(client);
        this.nationalId = client.getNationalId();
    }

    public Client toModel() {
        return new Client(this.getId(), this.isDeleted(), this.getNationalId());
    }
}
