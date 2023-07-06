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

    private String status;

    private String method;

    private String qrData;

    public Payment toDomain() { return new Payment(PaymentStatus.valueOf(status), PaymentMethod.valueOf(method), qrData); }

    public static PaymentDTO toDto(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setStatus(payment.getStatus().toString());
        paymentDTO.setMethod(payment.getMethod().toString());
        paymentDTO.setQrData(payment.getQrData());


        return paymentDTO;
    }

}
