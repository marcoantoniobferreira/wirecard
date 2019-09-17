package br.com.wirecard.payment;

import java.time.LocalDate;

import br.com.wirecard.order.Order;

/**
 * CreditCard
 */
public class CreditCard implements Payment {
    String holderName;
    long cardNumber;
    LocalDate expirationDate;
    int CVV;

    @Override
    public PaymentStatus createPayment(Order order) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PaymentStatus cancelPayment(Order order) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PaymentStatus checkPaymentStatus(Order order) {
        // TODO Auto-generated method stub
        return null;
    }
}