package br.com.grupo63.techchallenge.adapter.out.repository.order.entity;

import br.com.grupo63.techchallenge.adapter.out.repository.DomainEntity;
import br.com.grupo63.techchallenge.adapter.out.repository.client.entity.ClientEntity;
import br.com.grupo63.techchallenge.adapter.out.repository.payment.entity.PaymentEntity;
import br.com.grupo63.techchallenge.core.domain.model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "ord_order", indexes = {})
public class OrderEntity extends DomainEntity {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Order.Status status;

    @Basic
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @JoinColumn(name = "client", foreignKey = @ForeignKey(name = "fk_order_client"), nullable = false)
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private ClientEntity client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderItemEntity> items = new ArrayList<>();

    @JoinColumn(name = "payment", foreignKey = @ForeignKey(name = "fk_order_payment"))
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PaymentEntity payment;

    public OrderEntity(Order order) {
        super(order);
        this.status = order.getStatus();
        this.totalPrice = order.getTotalPrice();
        this.client = new ClientEntity(order.getClient());
        this.payment = new PaymentEntity(order.getPayment());
        this.items = order.getItems().stream().map(OrderItemEntity::new).toList();
    }

    public Order toModel() {
        return new Order(
                this.getStatus(),
                this.getTotalPrice(),
                this.getClient().toModel(),
                this.getItems().stream().map(OrderItemEntity::toModel).toList(),
                this.getPayment().toModel());
    }

}
