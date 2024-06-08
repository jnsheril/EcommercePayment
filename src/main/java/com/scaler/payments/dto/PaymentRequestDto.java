package com.scaler.payments.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class PaymentRequestDto {

    private String orderId;
    private Long paymentAmount;


}
