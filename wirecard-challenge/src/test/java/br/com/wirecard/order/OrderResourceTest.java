package br.com.wirecard.order;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

/**
 * OrderResourceTest
 */
@QuarkusTest
public class OrderResourceTest {
    @Test
    public void testCreateOrder(){       
        given()
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .body("{\"amount\":\"150.00\",\"type\":\"BO\",\"buyer\": {\"id\":\"1\"}}\"")
        .when()
            .post("/v1/order")
        .then()
            .statusCode(201)
            .body("amount", equalTo(150f),
                  "type", equalTo("BO"),
                  "buyer.id", equalTo(1));          
            
    }
    @Test
    public void tryCreateOrderWithoutBuyer(){
        given()
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .body("{\"amount\":\"150.00\",\"type\":\"BO\",\"buyer\": {\"id\":\"0\"}}\"")
        .when()
            .post("/v1/order")
        .then()
            .statusCode(404);                        
    }
    @Test
    public void getOrder(){
        given()
            .header("Content-Type", MediaType.APPLICATION_JSON)
        .when()
            .get("/v1/order")
        .then()
            .statusCode(200)
            .body("amount", containsInAnyOrder(150f),
                  "type", containsInAnyOrder("BO"),
                  "buyer.id", containsInAnyOrder(1));
    }
    
}