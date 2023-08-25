package br.com.grupo63.techchallenge.adapter;

import br.com.grupo63.techchallenge.api.controller.payment.dto.PaymentStatusResponseDTO;
import br.com.grupo63.techchallenge.api.controller.payment.dto.QRCodeResponseDTO;
import br.com.grupo63.techchallenge.controller.dto.PaymentControllerDTO;
import br.com.grupo63.techchallenge.entity.payment.Payment;
import br.com.grupo63.techchallenge.entity.payment.PaymentStatus;

public class PaymentAdapter {

    public static void fillEntity(PaymentControllerDTO dto, Payment entity) {
        entity.setStatus(dto.getStatus());
        entity.setMethod(dto.getMethod());
        entity.setQrData(dto.getQrData());
    }

}
