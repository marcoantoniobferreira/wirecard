package br.com.wirecard.payment;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.wirecard.order.Order;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * CreditCard
 */
@Entity
public class CreditCard extends PanacheEntity implements PaymentStrategy{
    @NotNull
    public String holderName;
    @Size(min = 16, max = 16, message 
    = "The number of card must have 16 digits")
    public long number;
    public LocalDate expirationDate;
    public String paymentStatus;
    public short cvv;  
    @OneToOne
    @JoinColumn(name = "orders_id")  
    public Order order;

    @Override
    public void processPayment() {
        this.order.type = "CC";
        this.paymentStatus = "ACCEPTED";
    }
    @Override
    public void cancelPayment() {
        this.paymentStatus = "CANCELLED";
    }
}