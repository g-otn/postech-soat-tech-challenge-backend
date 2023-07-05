package br.com.grupo63.techchallenge.core.domain.entity;

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
public class Order extends DomainEntity {

    @AllArgsConstructor
    public
    enum Status {
        RECEIVED("Recebido"), PREPARING("Em preparação"), READY("Pronto"), DONE("Finalizado");

        private String name;
    }

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Basic
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @JoinColumn(name = "client", foreignKey = @ForeignKey(name = "fk_order_client"), nullable = false)
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();

    @JoinColumn(name = "payment", foreignKey = @ForeignKey(name = "fk_order_payment"))
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

}
