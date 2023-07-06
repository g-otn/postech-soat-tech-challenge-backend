package br.com.grupo63.techchallenge.adapter.out.repository.order.entity;

import br.com.grupo63.techchallenge.adapter.out.repository.DomainEntity;
import br.com.grupo63.techchallenge.adapter.out.repository.product.entity.ProductEntity;
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
public class OrderItemEntity extends DomainEntity {

    @Basic
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Basic
    @Column(name = "price", nullable = false)
    private Double price;

    @JoinColumn(name = "ori_order", foreignKey = @ForeignKey(name = "fk_order_item_order"), nullable = false)
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private OrderEntity order;

    @JoinColumn(name = "product", foreignKey = @ForeignKey(name = "fk_order_item_product"), nullable = false)
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private ProductEntity product;

    public OrderItemEntity(Long quantity, Double price, Long productId) {
        this.quantity = quantity;
        this.price = price;
        this.product.setId(productId);
    }
}
