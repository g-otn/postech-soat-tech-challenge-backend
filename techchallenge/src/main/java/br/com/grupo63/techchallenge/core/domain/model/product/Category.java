package br.com.grupo63.techchallenge.core.domain.model.product;

import br.com.grupo63.techchallenge.core.domain.model.Domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Category extends Domain {

    private String name;

    public Category(Long id, boolean deleted, String name) {
        super(id, deleted);
        this.name = name;
    }

    public Category(Long id) {
        this.id = id;
    }
}
