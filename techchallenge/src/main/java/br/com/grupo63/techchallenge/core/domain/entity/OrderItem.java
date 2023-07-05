package br.com.grupo63.techchallenge.core.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "ori_order_item", indexes = {})
public class OrderItem extends DomainEntity {

    @Basic
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Basic
    @Column(name = "price", nullable = false)
    private Double price;

    @JoinColumn(name = "ori_order", foreignKey = @ForeignKey(name = "fk_order_item_order"), nullable = false)
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Order order;

    @JoinColumn(name = "product", foreignKey = @ForeignKey(name = "fk_order_item_product"), nullable = false)
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Product product;

    public OrderItem(Long quantity, Double price, Long productId) {
        this.quantity = quantity;
        this.price = price;
        this.product.setId(productId);
    }
}
