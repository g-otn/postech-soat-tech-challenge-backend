package br.com.grupo63.techchallenge.adapter;

import br.com.grupo63.techchallenge.entity.payment.Payment;
import br.com.grupo63.techchallenge.controller.dto.PaymentControllerDTO;

public class PaymentAdapter {

    public static PaymentControllerDTO toDto(Payment entity) {
        PaymentControllerDTO dto = new PaymentControllerDTO();

        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setMethod(entity.getMethod());
        dto.setQrData(entity.getQrData());

        return dto;
    }

    public static void fillEntity(PaymentControllerDTO dto, Payment entity) {
        entity.setStatus(dto.getStatus());
        entity.setMethod(dto.getMethod());
        entity.setQrData(dto.getQrData());
    }

}
