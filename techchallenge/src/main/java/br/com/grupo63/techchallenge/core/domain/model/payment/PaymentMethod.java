package br.com.grupo63.techchallenge.core.domain.model.payment;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PaymentMethod {
    MERCADO_PAGO_QR_CODE("QR Code Mercado Pago");

    private String name;
}
