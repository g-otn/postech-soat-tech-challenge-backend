package br.com.grupo63.techchallenge.core.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Product extends Domain {

    private String name;
    private Double price;
    private Double quantity;
    private Category category;

    public Product(Long id) {
        this.id = id;
    }

    public Product(Long id, boolean deleted, String name, Double price, Double quantity, Category category) {
        super(id, deleted);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }
}
