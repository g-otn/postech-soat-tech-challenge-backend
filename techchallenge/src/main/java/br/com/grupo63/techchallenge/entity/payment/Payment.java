package br.com.grupo63.techchallenge.entity.payment;

import br.com.grupo63.techchallenge.entity.Entity;
import br.com.grupo63.techchallenge.entity.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Payment extends Entity {


    private PaymentStatus status;
    private PaymentMethod method;
    private String qrData;
    private Order order;

    public Payment(Long id, boolean deleted, PaymentStatus status, PaymentMethod method, String qrData, Order order) {
        super(id, deleted);
        this.status = status;
        this.method = method;
        this.qrData = qrData;
        this.order = order;
    }
}
