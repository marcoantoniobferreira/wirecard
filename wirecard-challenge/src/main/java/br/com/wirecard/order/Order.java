package br.com.wirecard.order;

import java.math.BigDecimal;

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
}