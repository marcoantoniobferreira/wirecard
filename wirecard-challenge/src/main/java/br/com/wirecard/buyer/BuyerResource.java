package br.com.wirecard.buyer;

import java.util.List;

import javax.json.Json;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
/**
 * BuyerResource
 */
@Path("v1/buyer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BuyerResource {   
	@POST
    @Transactional
    public Response createBuyer(Buyer buyer){   
        buyer.id = null;     
        buyer.persist();
        return Response.ok(buyer).status(201).build();
    }
    @GET
    public List<Buyer> listBuyers(){
        return Buyer.listAll();
    } 
    /*@Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {
        @Override
        public Response toResponse(Exception exception) {
            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }
            return Response.status(code)
                    .entity(Json.createObjectBuilder().add("error", exception.getMessage()).add("code", code).build())
                    .build();
        }
    }*/
}