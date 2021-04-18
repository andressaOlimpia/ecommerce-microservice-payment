package com.digitalinnovation.ecommerce.payment.listener;

import com.digitalinnovation.ecommerce.payment.event.PaymentCreatedEvent;
import com.digitalinnovation.ecommerce.payment.model.Payment;
import com.digitalinnovation.ecommerce.payment.service.PaymentService;
import com.digitalinnovation.ecommercecheckout.event.CheckoutCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.digitalinnovation.ecommerce.payment.streaming.CheckoutProcessor;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckoutCreatedListener {

    private final CheckoutProcessor checkoutProcessor;
    private final PaymentService paymentService;

    @StreamListener(CheckoutProcessor.INPUT)
    public void handler(CheckoutCreatedEvent event){

        log.info("checkoutCreatedEvent={}", event);

        final Payment payment = paymentService.create(event).orElseThrow();
        final PaymentCreatedEvent paymentCreatedEvent = PaymentCreatedEvent.newBuilder()
                .setCheckoutCode(payment.getCheckoutCode())
                .setPaymentCode(payment.getCode())
                .build();
        checkoutProcessor.output().send(MessageBuilder.withPayload(paymentCreatedEvent).build());

    }
}
