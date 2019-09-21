package br.com.wirecard.buyer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;


/**
 * BuyerResourceTest
 */
@QuarkusTest
public class BuyerResourceTest {
    @Test
    public void testCreateBuyer(){
        given()
            .body("{\"cpf\":\"111.111.111-11\",\"email\":\"marco@marco.com\",\"name\":\"Marco\",\"id\":\"0\"}")
            .header("Content-Type", MediaType.APPLICATION_JSON)
        .when()
            .post("/v1/buyer")
        .then()
            .statusCode(201)
            .body("cpf", equalTo("111.111.111-11"),
                  "email", equalTo("marco@marco.com"),
                  "name", equalTo("Marco"));           
    }    
    @Test
    public void listBuyers(){
        given()
            .header("Content-Type", MediaType.APPLICATION_JSON)
        .when()
            .get("/v1/buyer")
        .then()
            .statusCode(200)
            .body("cpf", containsInAnyOrder("111.111.111-11"),
                  "email", containsInAnyOrder("marco@marco.com"),
                  "name", containsInAnyOrder("Marco"));
    }
}