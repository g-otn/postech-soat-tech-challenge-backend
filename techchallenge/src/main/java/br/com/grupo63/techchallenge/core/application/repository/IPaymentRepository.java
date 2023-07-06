package br.com.grupo63.techchallenge.core.application.repository;

import br.com.grupo63.techchallenge.core.domain.model.payment.Payment;

import java.util.Optional;

public interface IPaymentRepository extends IRepository<Payment> {

    Payment saveAndFlush(Payment payment);

    Optional<Payment> findByIdAndDeletedFalse(Long id);

}
