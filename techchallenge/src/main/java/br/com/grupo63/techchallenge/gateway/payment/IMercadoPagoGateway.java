package br.com.grupo63.techchallenge.gateway.payment;

public interface IMercadoPagoGateway {

    String generateQRCode(Long id, Double transactionAmount);

}
