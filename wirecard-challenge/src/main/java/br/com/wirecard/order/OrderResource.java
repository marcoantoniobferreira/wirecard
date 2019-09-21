package br.com.wirecard.order;

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

import br.com.wirecard.buyer.Buyer;

/**
 * OrderResource
 */
@Path("v1/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    @Transactional
    @POST
    public Response createOrder(Order order){
        Buyer buyer = Buyer.findById(order.buyer.id);    
        if (buyer == null) {
            throw new WebApplicationException("The buyer with id " + order.buyer.id + " does not exist", 404);
        }
        order.id = null;
        order.persist();
        return Response.ok(order).status(201).build();
    }    
    @GET
    public List<Order> getOrder(){
        return Order.listAll();
    }   
}