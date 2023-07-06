package br.com.grupo63.techchallenge.adapter.out.repository.payment;

import br.com.grupo63.techchallenge.adapter.out.repository.payment.entity.PaymentEntity;
import br.com.grupo63.techchallenge.core.application.repository.IPaymentRepository;
import br.com.grupo63.techchallenge.core.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long>, IPaymentRepository {



}