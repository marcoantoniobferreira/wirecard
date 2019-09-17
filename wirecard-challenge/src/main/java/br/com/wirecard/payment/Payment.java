package br.com.wirecard.payment;

import br.com.wirecard.order.Order;

/**
 * Payment
 */
public interface Payment {
    PaymentStatus createPayment(Order order);
    PaymentStatus cancelPayment(Order order);
    PaymentStatus checkPaymentStatus(Order order);    
}