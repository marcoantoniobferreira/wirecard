package br.com.wirecard.payment;

import java.time.LocalDate;
import java.util.Objects;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.wirecard.buyer.Buyer;
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
    public long cardNumber;   
    @JsonbDateFormat(value = "yyyy-MM-dd") 
    public LocalDate expirationDate;
    public String paymentStatus;
    public short cvv;  
    @OneToOne
    @JoinColumn(name = "orders_id")  
    public Order order;
    
    @Deprecated
    CreditCard(){}
    CreditCard(String holderName, long cardNumber, LocalDate expirationDate, String paymentStatus, short cvv, Order order){
        this.holderName = holderName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.paymentStatus = paymentStatus;
        this.cvv = cvv;
        this.order = order;
    }
    @Override
    public void processPayment() {
        this.order.type = "CC";
        this.paymentStatus = "ACCEPTED";
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
        CreditCard creditCard = CreditCard.builder().withCardNumber(checkout.cardNumber)
            .withCvv(checkout.cvv)
            .withExpirationDate(checkout.expirationDate)
            .withHolderName(checkout.name)
            .withOrder(order)
            .withPaymentStatus("ACCEPTED")
            .build();
        creditCard.persist();   
        return order.id;
    }
    public static CreditCardBuilder builder(){
        return new CreditCardBuilder();
    }
    public static class CreditCardBuilder{
        private String holderName;
        private long cardNumber;
        private LocalDate expirationDate;
        private String paymentStatus;
        private short cvv;
        private Order order;
        private CreditCardBuilder(){}
        public CreditCardBuilder withHolderName(String holderName){
            this.holderName = Objects.requireNonNull(holderName, "The holder name must be informed");
            return this;
        }
        public CreditCardBuilder withCardNumber(long cardNumber){
            this.cardNumber = Objects.requireNonNull(cardNumber, "The card number must be informed");
            return this;
        }
        public CreditCardBuilder withExpirationDate(LocalDate expirationDate){
            this.expirationDate = Objects.requireNonNull(expirationDate, "The expiration date must be informed");
            return this;
        }
        public CreditCardBuilder withPaymentStatus(String paymentStatus){
            this.paymentStatus = Objects.requireNonNull(paymentStatus, "The payment status must be informed");
            return this;
        }
        public CreditCardBuilder withCvv(short cvv){
            this.cvv = Objects.requireNonNull(cvv, "The CVV must be informed");
            return this;
        }
        public CreditCardBuilder withOrder(Order order){
            this.order = Objects.requireNonNull(order, "The order must be informed");
            return this;
        }
        public CreditCard build(){
            return new CreditCard(holderName, cardNumber, expirationDate, paymentStatus, cvv, order);
        }
    }
}