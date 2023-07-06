package br.com.grupo63.techchallenge.adapter.out.repository.product.entity;


import br.com.grupo63.techchallenge.adapter.out.repository.DomainEntity;
import br.com.grupo63.techchallenge.core.domain.model.Category;
import br.com.grupo63.techchallenge.core.domain.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter

@Entity
@Table(name = "prd_product", indexes = {})
public class ProductEntity extends DomainEntity {

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "price", nullable = false)
    private Double price;

    @Basic
    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @JoinColumn(name = "category", foreignKey = @ForeignKey(name = "fk_product_category"), nullable = false)
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private CategoryEntity category;

    public ProductEntity(Product product) {
        super(product);
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.category = new CategoryEntity(product.getCategory());
    }

    public Product toModel() {
        return new Product(this.getId(),
                this.isDeleted(),
                this.getName(),
                this.getPrice(),
                this.getQuantity(),
                this.getCategory().toModel());
    }
}
