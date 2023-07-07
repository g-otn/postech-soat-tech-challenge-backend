package br.com.grupo63.techchallenge.adapter.out.repository.order.entity;

import br.com.grupo63.techchallenge.adapter.out.repository.DomainEntity;
import br.com.grupo63.techchallenge.core.domain.model.Order;
import br.com.grupo63.techchallenge.core.domain.model.payment.Payment;
import br.com.grupo63.techchallenge.core.domain.model.payment.PaymentMethod;
import br.com.grupo63.techchallenge.core.domain.model.payment.PaymentStatus;
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
@Table(name = "pay_payment", indexes = {})
public class PaymentEntity extends DomainEntity {


    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "method", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Column(name = "qr_data", length = 200)
    private String qrData;

    @JoinColumn(name = "pay_order", foreignKey = @ForeignKey(name = "fk_payment_order"), nullable = false)
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private OrderEntity order;

    public PaymentEntity(Order order) {
        super(order.getPayment());
        this.status = order.getPayment().getStatus();
        this.method = order.getPayment().getMethod();
        this.qrData = order.getPayment().getQrData();
        this.order = new OrderEntity(order.getId());
    }

    public Payment toModel() {
        return new Payment(
                this.getId(),
                this.isDeleted(),
                status,
                method,
                qrData,
                null);
    }

}
