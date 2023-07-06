package br.com.grupo63.techchallenge.core.domain.model.payment;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PaymentStatus {
    PENDING("Pendente"), PAID("Pago");

    private String name;
}
