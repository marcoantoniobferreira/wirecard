package br.com.wirecard.order;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.wirecard.buyer.Buyer;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
/**
 * Order
 */
@Entity
@Table(name="Orders")
public class Order extends PanacheEntity{
   public BigDecimal amount;
   @JoinColumn(name = "buyer_id")
   @OneToOne
   public Buyer buyer;
   public String type;
   @Deprecated
   Order(){}
   Order(BigDecimal amount, Buyer buyer, String type){
      this.amount = amount;
      this.buyer = buyer;
      this.type = type;   
   }
   public static OrderBuilder builder(){
      return new OrderBuilder();
   }
   public static class OrderBuilder{
      private Buyer buyer;
      private BigDecimal amount;
      private String type;
      private OrderBuilder(){}
      public OrderBuilder withAmount(BigDecimal amount){
         this.amount = Objects.requireNonNull(amount, "The amount must be informed");
         return this;
      }
      public OrderBuilder withBuyer(Buyer buyer){
         this.buyer = Objects.requireNonNull(buyer, "The buyer must be informed");
         return this;
      }
      public OrderBuilder withType(String type){
         this.type = Objects.requireNonNull(type, "The type must be informed");
         return this;
      }
      public Order build(){
         return new Order(amount, buyer, type);
      }

   }
}