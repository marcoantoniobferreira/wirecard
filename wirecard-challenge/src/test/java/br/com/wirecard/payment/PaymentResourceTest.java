package br.com.wirecard.payment;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import javax.ws.rs.core.MediaType;

@QuarkusTest
public class PaymentResourceTest {
    @Test
    public void testCreatePaymentWithBoleto(){
        given()
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .body("{\"ownNumber\":\"0\",\"paymentStatus\":\"ACCEPTED\",\"order\":{\"id\":\"2\"}}\"")
        .when()
            .post("/v1/payment/boleto")
        .then()
            .statusCode(201)
            .body("ownNumber", equalTo("111111111111111111111111111111111111111111111"),
                  "paymentStatus", equalTo("ACCEPTED"),
                  "order.id", equalTo(2));
    }
    @Test
    public void testCreatePaymentWithCreditCard(){
        given()
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .body("{\"cvv\":\"111\",\"expirationDate\":\"2019-09-21\",\"holderName\":\"Marco\",\"number\":\"0000000000000000\",\"paymentStatus\":\"ACCEPTED\",\"order\":\"{\"id\":\"1\"}")
        .when()
            .post("/v1/payment/creditCard")
        .then()
            .statusCode(201)
            .body("cvv", equalTo("111"),
                  "expirationDate", equalTo("2019-09-21"),
                  "holderName", equalTo("Marco"),
                  "number", equalTo("000000000000000"),
                  "paymentStatus", equalTo("ACCEPTED"),
                  "order.id", equalTo(3));
    }   
    @Test
    public void testGetPayment(){
        given()
            .pathParam("id", 3)
            .header("Content-Type", MediaType.APPLICATION_JSON)
        .when()
            .get("/v1/payment/paymentStatus/{id}")
        .then()
            .statusCode(200)
            .body("cvv", equalTo("111"),
                  "expirationDate", equalTo("2019-09-21"),
                  "holderName", equalTo("Marco"),
                  "number", equalTo("0000000000000000"),
                  "paymentStatus", equalTo("ACCEPTED"),
                  "order.id", equalTo(3));
    }
}