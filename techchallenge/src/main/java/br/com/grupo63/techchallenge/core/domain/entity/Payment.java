package br.com.grupo63.techchallenge.core.domain.entity;

import br.com.grupo63.techchallenge.core.domain.entity.DomainEntity;
import br.com.grupo63.techchallenge.core.domain.entity.Order;
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
public class Payment extends DomainEntity {
    public Payment(Status status, Method method, String qrData) {
        this.status = status;
        this.method = method;
        this.qrData = qrData;
    }

    @AllArgsConstructor
    public enum Status {
        PENDING("Pendente"), PAID("Pago");

        private String name;
    }

    @AllArgsConstructor
    public enum Method {
        MERCADO_PAGO_QR_CODE("QR Code Mercado Pago");

        private String name;
    }

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "method", nullable = false)
    @Enumerated(EnumType.STRING)
    private Method method;

    @Column(name = "qr_data", length = 200)
    private String qrData;

    @JoinColumn(name = "pay_order", foreignKey = @ForeignKey(name = "fk_payment_order"), nullable = false)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Order order;

}
