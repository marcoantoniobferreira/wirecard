package br.com.wirecard.payment;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.wirecard.order.Order;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * Boleto
 */
@Entity
public class Boleto extends PanacheEntity implements PaymentStrategy{
    public String ownNumber; 
    public String paymentStatus; 
    @JoinColumn(name = "orders_id")  
    @OneToOne
    public Order order;

    @Override
    public void processPayment() {
        this.ownNumber = "111111111111111111111111111111111111111111111";
        this.paymentStatus = "ACCEPTED";
        this.order.type = "BO";
    }
    @Override
    public void cancelPayment() {
        this.paymentStatus = "CANCELLED";
    }
}