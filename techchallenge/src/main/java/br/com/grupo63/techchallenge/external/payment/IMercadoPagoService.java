package br.com.grupo63.techchallenge.external.payment;

public interface IMercadoPagoService {

    String generateQRCode(Long id, Double transactionAmount);

}
