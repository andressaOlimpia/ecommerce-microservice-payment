package com.digitalinnovation.ecommerce.payment.service;

import java.util.Optional;

import com.digitalinnovation.ecommerce.payment.model.Payment;
import com.digitalinnovation.ecommercecheckout.event.CheckoutCreatedEvent;

public interface PaymentService {

    Optional<Payment> create(CheckoutCreatedEvent checkoutCreatedEvent);
}
