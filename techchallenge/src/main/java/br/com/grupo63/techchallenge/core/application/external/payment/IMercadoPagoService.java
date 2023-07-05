package br.com.grupo63.techchallenge.core.application.external.payment;

import jakarta.validation.constraints.NotNull;

public interface IMercadoPagoService {

    String generateQRCode(@NotNull Long id, @NotNull Double transactionAmount);

}
