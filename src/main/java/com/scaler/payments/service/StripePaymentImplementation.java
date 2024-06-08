package com.scaler.payments.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentImplementation implements PaymentService{
//    @Override
//    public String makePayment(String a, Long b) throws StripeException {
//        return "";
//   }
    @Override
    public String makePayment(String orderId, Long amount) throws StripeException {

    // First of all we create a Price Object- it has order id,amount,currency.
    // Below process will create a priceObject for us.
        Stripe.apiKey ="sk_test_51PO1MbHiZQsjRWdaWLljT8OuUZL56aV5qtMAntmeziPOEHZUL4BpezcINQIOXAOPxQld4VsA4XZjy6rjqODSHQ6N00H8CTszfR";

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName(orderId).build()
                        )
                        .build();
        // it will go to hit internally to my Strip Account, it will go to get a unique id for price (object)
        Price price = Price.create(params);
        // now we have built Price object

        // now we need to create payment link:
        //Stripe.apiKey = "sk_test_51PO1MbHiZQsjRWdaWLljT8OuUZL56aV5qtMAntmeziPOEHZUL4BpezcINQIOXAOPxQld4VsA4XZjy6rjqODSHQ6N00H8CTszfR";

        PaymentLinkCreateParams paymentParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("https://credwise.co.in")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentParams);
        return paymentLink.getUrl();
    }


}
