package br.com.grupo63.techchallenge.adapter.out.external;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MercadoPagoService {

    public String generateQRCode(Double total) {
        // This random UUID represents a MercadoPago hash to generate a payment QR code
        return UUID.randomUUID().toString().replace("-", "");
    }
}
