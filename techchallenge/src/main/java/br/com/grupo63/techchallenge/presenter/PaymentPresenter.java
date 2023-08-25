package br.com.grupo63.techchallenge.presenter;

import br.com.grupo63.techchallenge.api.controller.payment.dto.PaymentStatusResponseDTO;
import br.com.grupo63.techchallenge.api.controller.payment.dto.QRCodeResponseDTO;
import br.com.grupo63.techchallenge.controller.dto.PaymentControllerDTO;
import br.com.grupo63.techchallenge.entity.payment.Payment;
import br.com.grupo63.techchallenge.entity.payment.PaymentStatus;

public class PaymentPresenter {

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


}
