package br.com.wirecard.buyer;

import java.util.Objects;

import br.com.wirecard.util.Email;

/**
 * Buyer
 */
public class Buyer {    
    private long id;
    private String name;
    private Email email;
    private String cpf;
    Buyer(long id, String name, Email email, String cpf){
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }    
    @Deprecated
    Buyer(){}
    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }
    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    /**
     * @return the email
     */
    public Email getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(Email email) {
        this.email = email;
    }
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    public static class BuyerBuilder{
        private String name;
        private long id;
        private Email email;
        private String cpf;
        private BuyerBuilder(){}
        private BuyerBuilder(Buyer buyer){
            this.name = buyer.name;
            this.id = buyer.id;
            this.cpf = buyer.cpf;
            this.email = buyer.email;
        }
        public BuyerBuilder withName(String name){
            this.name = Objects.requireNonNull(name, "Name is required");
            return this;
        }
        public BuyerBuilder withId(long id){
            this.id = Objects.requireNonNull(id, "id is required");
            return this;
        }
        public BuyerBuilder withEmail(Email email){
            this.email = Objects.requireNonNull(email, "email is required");
            return this;
        }
        public BuyerBuilder withCpf(String cpf){
            this.cpf = Objects.requireNonNull(cpf, "cpf is required");
            return this;
        }
        public Buyer build(){
            Objects.requireNonNull(name, "name is required");
            Objects.requireNonNull(id, "id is required");
            Objects.requireNonNull(email, "email is required");
            Objects.requireNonNull(cpf, "cpf is required");
            return new Buyer(id, name, email, cpf);
        }
    }
}