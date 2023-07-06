package br.com.grupo63.techchallenge.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Payment extends Domain {

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

    private Status status;
    private Method method;
    private String qrData;
    private Order order;

    public Payment(Status status, Method method, String qrData) {
        this.status = status;
        this.method = method;
        this.qrData = qrData;
    }
}
