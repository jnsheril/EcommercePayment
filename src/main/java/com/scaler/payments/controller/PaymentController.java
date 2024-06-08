package com.scaler.payments.controller;

import com.stripe.exception.StripeException;
import com.scaler.payments.dto.PaymentRequestDto;
import org.springframework.web.bind.annotation.*;
import com.scaler.payments.service.PaymentService;

@RestController
//@RequestMapping("/payments")

//In this case we need to create
// We want info from client - orderId, amount
// to Link PaymentService to PaymentController - We need to inject dependencies
public class PaymentController {
    // Link
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payments")
    // Passing info from client as request Body.
    public String createPaymentLink(@RequestBody PaymentRequestDto paymentRequestDto) throws StripeException {
        // we need to pass in Request Body:  paymentRequestDto field

        // we need to pass : below variable of dto class
        // String variable as : productServiceDto.getOrderId()
        // Long variable as : productServiceDto.getPaymentAmount()
        return paymentService.makePayment(paymentRequestDto.getOrderId(), paymentRequestDto.getPaymentAmount());
    }

    // how to do it as Response Entity:

//    public ResponseEntity<String> createPaymentLink(
//            @RequestBody PaymentRequestDto paymentRequestDto) throws StripeException {
//
//        String paymentLink = paymentService.makePayment(
//                paymentRequestDto.getOrderId(), paymentRequestDto.getPaymentAmount());
//
//        return new ResponseEntity<>(paymentLink, HttpStatus.OK);
//    }

    @PostMapping("/webhooks")
    public String handleWebhook(){
        System.out.println("Webhook Request is recevied");
        return "";
    }

 }
