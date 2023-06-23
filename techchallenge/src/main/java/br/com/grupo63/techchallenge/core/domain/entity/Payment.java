package br.com.grupo63.techchallenge.core.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "pay_payment", indexes = {})
public class Payment extends DomainEntity {

    @AllArgsConstructor
    enum PaymentStatus {
        PENDING("Pendente"), PAID("Pago");

        private String name;
    }

    @AllArgsConstructor
    enum PaymentMethod {
        MERCADO_PAGO_QR_CODE("QR Code Mercado Pago");

        private String name;
    }

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @JoinColumn(name = "pay_order", foreignKey = @ForeignKey(name = "fk_payment_order"), nullable = false)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Order order;

}
