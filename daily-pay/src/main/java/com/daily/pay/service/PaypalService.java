package com.daily.pay.service;

import com.daily.common.enums.PaypalPaymentIntent;
import com.daily.common.enums.PaypalPaymentMethod;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

/**
 * @author Loyaill
 * @description :
 * @CreateTime 2018-07-31-14:24
 */
public interface PaypalService {

    Payment createPayment(
            Double total,
            String currency,
            PaypalPaymentMethod method,
            PaypalPaymentIntent intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException;


    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}
