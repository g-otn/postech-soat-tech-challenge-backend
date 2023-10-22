package br.com.grupo63.techchallenge.gateway.order.entity;

import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.entity.order.OrderStatus;
import br.com.grupo63.techchallenge.gateway.repository.entity.PersistenceEntity;
import br.com.grupo63.techchallenge.gateway.client.entity.ClientPersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "ord_order", indexes = {})
public class OrderPersistenceEntity extends PersistenceEntity {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Basic
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @JoinColumn(name = "client", foreignKey = @ForeignKey(name = "fk_order_client"), nullable = false)
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private ClientPersistenceEntity client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderItemPersistenceEntity> items = new ArrayList<>();

    @JoinColumn(name = "payment", foreignKey = @ForeignKey(name = "fk_order_payment"))
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PaymentPersistenceEntity payment;

    public OrderPersistenceEntity(Long id) {
        this.id = id;
    }

    public OrderPersistenceEntity(Order order) {
        super(order);
        this.status = order.getStatus();
        this.totalPrice = order.getTotalPrice();
        this.client = new ClientPersistenceEntity(order.getClient());
        this.payment = order.getPayment() != null ? new PaymentPersistenceEntity(order) : null;
        this.items = order.getItems().stream().map(item -> new OrderItemPersistenceEntity(item, this)).toList();
    }

    public Order toModel() {
        return new Order(
                this.getId(),
                this.isDeleted(),
                this.getStatus(),
                this.getTotalPrice(),
                this.getClient().toModel(),
                this.getItems().stream().map(OrderItemPersistenceEntity::toModel).toList(),
                this.getPayment() != null ? this.getPayment().toModel() : null);
    }
}
