package br.com.wirecard.payment;

import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import br.com.wirecard.order.Order;
/**
 * PaymentResource
 */
@Path("v1/payment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {    
    @POST
    @Transactional
    @Path("/boleto")
    public Response createPaymentWithBoleto(Boleto boleto){  
        System.out.println("Cheguei");      
        Order order = Order.findById(boleto.order.id);    
        if (order == null) {
            System.out.println("Não emcpmtrei order");
            System.out.println("Número do order: " + boleto.order.id);      
            throw new WebApplicationException("The order with id " + boleto.order.id + " does not exist", 404);
        }
        boleto.processPayment();
        boleto.id = null; 
        boleto.order.type = "BO";        
        boleto.persist();      
        order.type = "BO";    
        return Response.ok(boleto).status(201).build();
    }
    @POST
    @Transactional
    @Path("/creditCard")
    public Response createPaymentWithCreditCard(CreditCard creditCard){
        Order order = Order.findById(creditCard.order.id);
        if (order == null) {
            throw new WebApplicationException("The order with id " + creditCard.order.id + " does not exist", 404);
        }
        creditCard.processPayment();
        creditCard.id = null;
        creditCard.persist();
        order.type = "CC";        
        return Response.ok(creditCard).status(201).build();
    }
    @POST
    @Transactional
    @Path("/checkout")
    public Response createPaymentWithCheckout(Checkout checkout){
        if (checkout.paymentType.equals("creditCard")) {
            CreditCard cc = new CreditCard();
            cc.processPaymentWithCheckout(checkout);
        }else{
            Boleto bo = new Boleto();
            bo.processPaymentWithCheckout(checkout);
        }        
        return Response.ok(checkout).status(201).build();    
    }
    @GET
    @Path("/paymentStatus/{id}")
    public List getStatusPayment(@PathParam Long id){
        System.out.println(id);        
        Order order = Order.findById(id);
        if (order == null) {
            throw new WebApplicationException("The order with id " + id + " does not exist.", 404);
        }
        List paymentStatus;         
        if (order.type.equals("BO")) {            
            paymentStatus = Boleto.list("orders_id", order.id);
            System.out.println("É boleto");            
        }else{
            System.out.println("É cartão");
            paymentStatus = CreditCard.list("orders_id", order.id);
        }
        return paymentStatus;
    }
}