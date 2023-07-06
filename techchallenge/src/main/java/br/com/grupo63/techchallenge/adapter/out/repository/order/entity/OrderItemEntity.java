package br.com.grupo63.techchallenge.adapter.out.repository.order.entity;

import br.com.grupo63.techchallenge.adapter.out.repository.DomainEntity;
import br.com.grupo63.techchallenge.adapter.out.repository.product.entity.ProductEntity;
import br.com.grupo63.techchallenge.core.domain.model.OrderItem;
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

    public OrderItemEntity(OrderItem item) {
        super(item);
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
        this.order = new OrderEntity(item.getOrder());
        this.product = new ProductEntity(item.getProduct());
    }

    public OrderItem toModel() {
        return new OrderItem(this.getQuantity(), this.getPrice(), null, this.product.toModel());
    }

}
