package br.com.grupo63.techchallenge.adapter;

import br.com.grupo63.techchallenge.controller.dto.PaymentControllerDTO;
import br.com.grupo63.techchallenge.entity.payment.Payment;

public class PaymentAdapter {

    public static void fillEntity(PaymentControllerDTO dto, Payment entity) {
        entity.setStatus(dto.getStatus());
        entity.setMethod(dto.getMethod());
        entity.setQrData(dto.getQrData());
    }

}
