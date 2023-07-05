package br.com.grupo63.techchallenge.core.application.repository.payment;

import br.com.grupo63.techchallenge.core.application.repository.IRepository;
import br.com.grupo63.techchallenge.core.domain.entity.Payment;

import java.util.Optional;

public interface IPaymentRepository extends IRepository<Payment> {

    Payment saveAndFlush(Payment payment);

    Optional<Payment> findById(Long id);

}
