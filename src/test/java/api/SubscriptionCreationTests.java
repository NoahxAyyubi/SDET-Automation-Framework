package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscriptionCreationTests {

    @Test
    public void shouldCreateSubscriptionSuccessfully() {
        System.out.println("\n=== Running: shouldCreateSubscriptionSuccessfully ===");

        String requestBody = "{ \"id\": 9001, \"name\": \"test-subscription\", \"status\": \"available\" }";

        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("https://petstore.swagger.io/v2/pet");
        given().log().all()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("https://petstore.swagger.io/v2/pet")
                .then().log().all();

        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.getBody().asString());

        assertEquals(200, response.statusCode());
    }

    @Test
    public void shouldGetPetById() {
        System.out.println("\n=== Running: shouldGetPetById ===");

        String createBody = "{ \"id\": 9001, \"name\": \"test-subscription\", \"status\": \"available\" }";

        given().log().all()
                .header("Content-Type", "application/json")
                .body(createBody)
                .when()
                .post("https://petstore.swagger.io/v2/pet")
                .then().log().all();

        Response response = given().log().all()
                .when()
                .get("https://petstore.swagger.io/v2/pet/9001");
        given().log().all()
                .when()
                .get("https://petstore.swagger.io/v2/pet/9001")
                .then().log().all();

        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.getBody().asString());

        assertEquals(200, response.statusCode());
    }

    @Test
    public void shouldUpdatePet() {
        System.out.println("\n=== Running: shouldUpdatePet ===");

        // Step 1: ensure pet exists
        String createBody = "{ \"id\": 9001, \"name\": \"test-subscription\", \"status\": \"available\" }";

        given().log().all()
                .header("Content-Type", "application/json")
                .body(createBody)
                .when()
                .post("https://petstore.swagger.io/v2/pet")
                .then().log().all();

        // Step 2: update pet
        String updateBody = "{ \"id\": 9001, \"name\": \"updated-subscription\", \"status\": \"available\" }";

        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .body(updateBody)
                .when()
                .put("https://petstore.swagger.io/v2/pet");
        given().log().all()
                .header("Content-Type", "application/json")
                .body(updateBody)
                .when()
                .put("https://petstore.swagger.io/v2/pet")
                .then().log().all();

        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.getBody().asString());

        assertEquals(200, response.statusCode());
    }

    @Test
    public void shouldDeletePet() {
        System.out.println("\n=== Running: shouldDeletePet ===");

        // Step 1: ensure pet exists
        String createBody = "{ \"id\": 9001, \"name\": \"test-subscription\", \"status\": \"available\" }";

        given().log().all()
                .header("Content-Type", "application/json")
                .body(createBody)
                .when()
                .post("https://petstore.swagger.io/v2/pet")
                .then().log().all();

        Response response = given().log().all()
                .when()
                .delete("https://petstore.swagger.io/v2/pet/9001");
        given().log().all()
                .when()
                .delete("https://petstore.swagger.io/v2/pet/9001")
                .then().log().all();

        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.getBody().asString());

        assertEquals(200, response.statusCode());
    }


}