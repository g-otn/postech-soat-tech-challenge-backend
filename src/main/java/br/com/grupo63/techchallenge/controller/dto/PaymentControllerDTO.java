package br.com.grupo63.techchallenge.controller.dto;

import br.com.grupo63.techchallenge.entity.payment.PaymentMethod;
import br.com.grupo63.techchallenge.entity.payment.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentControllerDTO extends AbstractControllerDTO {

    private PaymentStatus status;

    private PaymentMethod method;

    private String qrData;

}
