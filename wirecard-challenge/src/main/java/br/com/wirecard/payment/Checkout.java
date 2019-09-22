package br.com.wirecard.payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * Checkout
 */
public class Checkout{
    public Checkout(){}
    public String name;
    public String email;
    public String paymentType;    
    public short cvv;    
    public String cpf;    
    public LocalDate expirationDate;
    public long cardNumber;
    public BigDecimal amount;    
}