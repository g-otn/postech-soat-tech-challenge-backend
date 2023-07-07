package br.com.grupo63.techchallenge.core.application.usecase.dto;

import br.com.grupo63.techchallenge.core.domain.model.payment.Payment;
import br.com.grupo63.techchallenge.core.domain.model.payment.PaymentMethod;
import br.com.grupo63.techchallenge.core.domain.model.payment.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDTO {

    private PaymentStatus status;

    private PaymentMethod method;

    private String qrData;

    public static PaymentDTO toDto(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setStatus(payment.getStatus());
        paymentDTO.setMethod(payment.getMethod());
        paymentDTO.setQrData(payment.getQrData());


        return paymentDTO;
    }

    public void toDomain(Payment payment) {
        payment.setStatus(status);
        payment.setMethod(method);
        payment.setQrData(qrData);
    }

}
