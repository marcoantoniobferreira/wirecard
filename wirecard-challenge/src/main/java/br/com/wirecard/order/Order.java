package br.com.wirecard.order;

import javax.money.MonetaryAmount;

import br.com.wirecard.payment.PaymentType;

/**
 * Order
 */
public class Order {
    private long id;
    private MonetaryAmount amount;
    private PaymentType paymentType;  
      
}