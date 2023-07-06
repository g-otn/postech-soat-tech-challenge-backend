package br.com.grupo63.techchallenge.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Client extends Domain {

    private String nationalId;

    public Client(Long id, boolean deleted, String nationalId) {
        super(id, deleted);
        this.nationalId = nationalId;
    }
}
