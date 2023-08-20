package br.com.grupo63.techchallenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public abstract class Entity implements Serializable {

    protected Long id;

    protected boolean deleted = false;

    public void delete() {
        this.deleted = true;
    }

}
