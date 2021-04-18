package com.digitalinnovation.ecommerce.payment.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import com.digitalinnovation.ecommerce.payment.repository.PaymentRepository;
import com.digitalinnovation.ecommerce.payment.model.Payment;
import com.digitalinnovation.ecommercecheckout.event.CheckoutCreatedEvent;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    @Override
    public Optional<Payment> create(CheckoutCreatedEvent checkoutCreatedEvent) {
        final Payment payment = Payment.builder()
                .checkoutCode(checkoutCreatedEvent.getCheckoutCode())
                .code(UUID.randomUUID().toString())
                .build();
        paymentRepository.save(payment);
        return Optional.of(payment);
    }
}
