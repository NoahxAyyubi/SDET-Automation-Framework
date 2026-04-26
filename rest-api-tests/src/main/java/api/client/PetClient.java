package api.client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import api.models.Pet;

public class PetClient {

    public Response createPet(Pet pet) {

        return given()

                .header(
                        "Content-Type",
                        "application/json"

                )

                .body(pet)

                .when()

                .post("/pet");

    }
    
    // ================= WORKBOOK STEP =================
    // This overload allows tests to send raw JSON from PayloadLoader
    // Example usage in test:
    // String body = PayloadLoader.load("createSubscriptionValid.json");
    // petClient.createPet(body);
    //
    // Why this exists:
    // createPet(Pet pet) expects a Java object
    // PayloadLoader returns a JSON String
    // We keep both methods so old tests do NOT break

    public Response createPet(String rawJsonBody) {

        return given()

                .header(
                        "Content-Type",
                        "application/json"
                )

                .body(rawJsonBody)

                .when()

                .post("/pet");

    }

    public Response getPet(int id) {

        return given()

        .when()

                .get("/pet/" + id);

    }

    public Response updatePet(String body) {

        return given()

                .header(
                    "Content-Type",
                    "application/json"
                )

                .body(body)

        .when()

                .put("/pet");

    }

    public Response deletePet(int id) {

        return given()

        .when()

                .delete("/pet/" + id);

    }

}