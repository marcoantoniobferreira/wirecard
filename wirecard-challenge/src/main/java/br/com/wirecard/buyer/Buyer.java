package br.com.wirecard.buyer;

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
}