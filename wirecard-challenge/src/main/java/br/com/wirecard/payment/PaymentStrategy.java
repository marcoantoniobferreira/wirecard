package br.com.wirecard.payment;

/**
 * PaymentStrategy
 */
public interface PaymentStrategy {
    void processPayment();
    long processPaymentWithCheckout(Checkout checkout);
    void cancelPayment();        
}