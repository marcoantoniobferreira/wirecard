package br.com.wirecard.payment;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.wirecard.buyer.Buyer;
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
    Boleto(String ownNumber, String paymentStatus, Order order){
        this.ownNumber = ownNumber;
        this.paymentStatus = paymentStatus;
        this.order = order;
    }
    @Deprecated
    Boleto(){}
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
    @Override
    public long processPaymentWithCheckout(Checkout checkout) {     
        Buyer buyer = Buyer.builder().withCpf(checkout.cpf)
            .withEmail(checkout.email)
            .withName(checkout.name)
            .build();
        buyer.persist();
        Order order = Order.builder().withAmount(checkout.amount)
            .withBuyer(buyer)
            .withType(checkout.paymentType)
            .build();
        order.persist();            
        Boleto boleto = Boleto.builder().withOwnNumber("111111111111111111111111111111111111111111111")
            .withOrder(order)
            .withPaymentStatus("ACCEPTED")
            .build();
        boleto.persist();                          
        return order.id;
    }
    public static BoletoBuilder builder(){
        return new BoletoBuilder();
    }
    public static class BoletoBuilder{
        private String ownNumber;
        private String paymentStatus;
        private Order order;
        public BoletoBuilder withOwnNumber(String ownNumber){
            this.ownNumber = Objects.requireNonNull(ownNumber, "The own number must bem informed");
            return this;
        }
        public BoletoBuilder withPaymentStatus(String paymentStatus){
            this.paymentStatus = Objects.requireNonNull(paymentStatus, "The payment status must be informed");
            return this;
        }
        public BoletoBuilder withOrder(Order order){
            this.order = Objects.requireNonNull(order, "The order must be informed");
            return this;
        }
        public Boleto build(){
            return new Boleto(ownNumber, paymentStatus, order);
        }
    }
}