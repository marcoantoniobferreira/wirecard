package br.com.wirecard.payment;

/**
 * PaymentStrategy
 */
public interface PaymentStrategy {
    void processPayment();
    void cancelPayment();        
}