package br.com.wirecard.payment;

import br.com.wirecard.order.Order;

/**
 * Boleto
 */
public class Boleto implements Payment {
    long number;

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