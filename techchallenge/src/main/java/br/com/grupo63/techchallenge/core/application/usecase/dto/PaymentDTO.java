package br.com.grupo63.techchallenge.core.application.usecase.dto;

import br.com.grupo63.techchallenge.core.domain.entity.Payment;
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

    public Payment toDomain() { return new Payment(Payment.Status.valueOf(status), Payment.Method.valueOf(method), qrData); }

    public static PaymentDTO toDto(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setStatus(payment.getStatus().toString());
        paymentDTO.setMethod(payment.getMethod().toString());
        paymentDTO.setQrData(payment.getQrData());


        return paymentDTO;
    }

}
