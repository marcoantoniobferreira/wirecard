package br.com.wirecard.buyer;

import java.util.Objects;

import javax.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * Buyer
 */
@Entity
public class Buyer extends PanacheEntity {   
    public String name;
    public String email;
    public String cpf;    
    @Deprecated
    Buyer(){}    
    Buyer(String name, String email, String cpf){
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }
    public static BuyerBuilder builder(){
        return new BuyerBuilder();
    }
    public static class BuyerBuilder{
        private String name;
        private String email;
        private String cpf;
        private BuyerBuilder(){}
        public BuyerBuilder withName(String name){
            this.name = Objects.requireNonNull(name, "The name must be informed");
            return this;
        }
        public BuyerBuilder withEmail(String email){
            this.email = Objects.requireNonNull(email, "The email must be informed");
            return this;
        }
        public BuyerBuilder withCpf(String cpf){
            this.cpf = Objects.requireNonNull(cpf, "The CPF must be informed");
            return this;
        }
        public Buyer build(){
            return new Buyer(name, email, cpf);
        }
    }
}