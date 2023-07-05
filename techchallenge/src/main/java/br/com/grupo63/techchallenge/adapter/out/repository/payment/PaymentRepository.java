package br.com.grupo63.techchallenge.adapter.out.repository.payment;

import br.com.grupo63.techchallenge.core.application.repository.payment.IPaymentRepository;
import br.com.grupo63.techchallenge.core.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long>, IPaymentRepository {



}