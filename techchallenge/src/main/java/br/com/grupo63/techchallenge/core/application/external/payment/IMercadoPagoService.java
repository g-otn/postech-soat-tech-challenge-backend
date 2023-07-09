package br.com.grupo63.techchallenge.core.application.external.payment;

public interface IMercadoPagoService {

    String generateQRCode(Long id, Double transactionAmount);

}
