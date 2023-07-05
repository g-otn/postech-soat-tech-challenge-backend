package br.com.grupo63.techchallenge.core.domain.entity;


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
public class Product extends DomainEntity {

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
    private Category category;

    public Product(Long productId) {
        this.id = productId;
    }
}
