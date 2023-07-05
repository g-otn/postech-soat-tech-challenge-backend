package br.com.grupo63.techchallenge.adapter.in.controller.payment.dto;

import br.com.grupo63.techchallenge.core.domain.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatusResponseDTO {

    private Payment.Status status;

}
