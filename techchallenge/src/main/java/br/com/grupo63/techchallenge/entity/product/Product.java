package br.com.grupo63.techchallenge.entity.product;


import br.com.grupo63.techchallenge.entity.Entity;
import br.com.grupo63.techchallenge.entity.validation.group.Create;
import br.com.grupo63.techchallenge.entity.validation.group.Update;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Product extends Entity {

    @NotEmpty(message = "product.name.notEmpty", groups = {Create.class, Update.class})
    @NotNull(message = "product.name.notNull", groups = {Create.class, Update.class})
    private String name;

    @NotNull(message = "product.price.notNull", groups = {Create.class, Update.class})
    @Min(message = "product.price.notLessThanZero", value = 0, groups = {Create.class, Update.class})
    private Double price;

    @NotNull(message = "product.category.notNull", groups = {Create.class, Update.class})
    private Category category;

    public Product(Long id) {
        this.id = id;
    }

    public Product(Long id, boolean deleted, String name, Double price, Category category) {
        super(id, deleted);
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
