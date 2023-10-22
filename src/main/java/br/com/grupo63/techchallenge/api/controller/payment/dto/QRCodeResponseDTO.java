package br.com.grupo63.techchallenge.api.controller.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QRCodeResponseDTO {

    private String qrData;

}
