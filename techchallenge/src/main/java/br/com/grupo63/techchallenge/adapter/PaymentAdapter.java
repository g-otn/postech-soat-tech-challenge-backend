package br.com.grupo63.techchallenge.adapter;

import br.com.grupo63.techchallenge.api.controller.payment.dto.PaymentStatusResponseDTO;
import br.com.grupo63.techchallenge.api.controller.payment.dto.QRCodeResponseDTO;
import br.com.grupo63.techchallenge.controller.dto.PaymentControllerDTO;
import br.com.grupo63.techchallenge.entity.payment.Payment;
import br.com.grupo63.techchallenge.entity.payment.PaymentStatus;

public class PaymentAdapter {

    // TODO: Perguntar se adapter substitui os presenters (mesma coisa)?
    public static PaymentControllerDTO toDto(Payment entity) {
        PaymentControllerDTO dto = new PaymentControllerDTO();

        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setMethod(entity.getMethod());
        dto.setQrData(entity.getQrData());

        return dto;
    }

    public static QRCodeResponseDTO toDto(String qrCode) {
        return new QRCodeResponseDTO(qrCode);
    }

    public static PaymentStatusResponseDTO toDto(PaymentStatus status) {
        return new PaymentStatusResponseDTO(status);
    }

    public static void fillEntity(PaymentControllerDTO dto, Payment entity) {
        entity.setStatus(dto.getStatus());
        entity.setMethod(dto.getMethod());
        entity.setQrData(dto.getQrData());
    }

}
