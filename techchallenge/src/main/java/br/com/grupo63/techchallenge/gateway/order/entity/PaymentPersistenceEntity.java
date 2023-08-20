package br.com.grupo63.techchallenge.gateway.order.entity;

import br.com.grupo63.techchallenge.gateway.PersistenceEntity;
import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.entity.payment.Payment;
import br.com.grupo63.techchallenge.entity.payment.PaymentMethod;
import br.com.grupo63.techchallenge.entity.payment.PaymentStatus;
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
public class PaymentPersistenceEntity extends PersistenceEntity {


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
    private OrderPersistenceEntity order;

    public PaymentPersistenceEntity(Order order) {
        super(order.getPayment());
        this.status = order.getPayment().getStatus();
        this.method = order.getPayment().getMethod();
        this.qrData = order.getPayment().getQrData();
        this.order = new OrderPersistenceEntity(order.getId());
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
