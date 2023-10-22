package br.com.grupo63.techchallenge.api.controller.payment.dto;

import br.com.grupo63.techchallenge.entity.payment.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatusResponseDTO {

    private PaymentStatus status;

}
