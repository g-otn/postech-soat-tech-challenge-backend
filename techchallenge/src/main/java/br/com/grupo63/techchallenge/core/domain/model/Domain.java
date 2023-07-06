package br.com.grupo63.techchallenge.core.domain.model;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PACKAGE)

public abstract class Domain implements Serializable {

    protected Long id;

    protected boolean deleted = false;

    public void delete() {
        this.deleted = true;
    }
}
