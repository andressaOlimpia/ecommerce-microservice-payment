package com.digitalinnovation.ecommerce.payment.repository;

import com.digitalinnovation.ecommerce.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
