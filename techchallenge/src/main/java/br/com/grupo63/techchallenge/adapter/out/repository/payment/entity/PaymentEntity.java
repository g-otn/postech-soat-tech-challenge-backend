package br.com.grupo63.techchallenge.adapter.out.repository.payment.entity;

import br.com.grupo63.techchallenge.adapter.out.repository.DomainEntity;
import br.com.grupo63.techchallenge.adapter.out.repository.order.entity.OrderEntity;
import br.com.grupo63.techchallenge.core.domain.model.payment.Payment;
import br.com.grupo63.techchallenge.core.domain.model.payment.PaymentMethod;
import br.com.grupo63.techchallenge.core.domain.model.payment.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private OrderEntity order;

    public PaymentEntity(@NotNull Payment payment) {
        super(payment);
        this.status = payment.getStatus();
        this.method = payment.getMethod();
        this.qrData = payment.getQrData();
        this.order = null;
    }

    public Payment toModel() {
        return new Payment(status, method, qrData, null);
    }

}
