package test;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import api.client.PetClient;
import api.utils.Config;
import org.junit.jupiter.api.BeforeAll;
import api.models.Pet;

public class SubscriptionCreationTests {

    // create reusable API client (links test -> client layer)
    static PetClient petClient = new PetClient();

       Pet pet = new Pet(
                9001,
                "test-subscription",
                "available"
        );


    // runs once before all tests (sets base URL from Config class)
    @BeforeAll
    static void setup() {
        Config.setup();
    }

    @Test
    public void shouldCreateSubscriptionSuccessfully() {
        System.out.println("\n=== Running: shouldCreateSubscriptionSuccessfully ===");

        // MODEL usage instead of raw JSON string
     
        // call reusable client method instead of writing HTTP request inline
        // FLOW:
        // test -> passes JSON body -> PetClient -> RestAssured -> API
        Response response = petClient.createPet(pet);

        // log response AFTER request is sent
        response.then().log().all();

        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.getBody().asString());

        assertEquals(200, response.statusCode());
    }

    @Test
    public void shouldGetPetById() {
        System.out.println("\n=== Running: shouldGetPetById ===");

        // baby step 1: make sure pet exists in system first
        // we send pet to API so later we can try to get it back
        petClient.createPet(pet);

        // baby step 2: call API to get pet by id
        // this sends GET request -> API -> returns JSON response
        Response response = petClient.getPet(9001);

        // print everything API sends back (for learning/debug)
        response.then().log().all();

        // baby step 3: convert JSON response into Pet object
        // "as(Pet.class)" means: take JSON and turn into Java object
        Pet returnedPet = response.as(Pet.class);

        // baby step 4: check status code is correct (API worked)
        assertEquals(200, response.statusCode());

        // baby step 5: compare expected vs actual values
        // expected = original pet we created
        // actual = pet returned from API
        assertEquals(pet.getName(), returnedPet.getName());
        assertEquals(pet.getStatus(), returnedPet.getStatus());
    }

    @Test
    public void shouldUpdatePet() {
        System.out.println("\n=== Running: shouldUpdatePet ===");

        // Step 1: ensure pet exists
        // String createBody = """
        // {
        //   "id": 9001,
        //   "name": "test-subscription",
        //   "status": "available"
        // }
        // """;

        petClient.createPet(pet);

        // Step 2: update pet
        String updateBody = """
        {
          "id": 9001,
          "name": "updated-subscription",
          "status": "available"
        }
        """;

        // update request routed through client layer
        Response response = petClient.updatePet(updateBody);

        response.then().log().all();

        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.getBody().asString());

        assertEquals(200, response.statusCode());
    }

    @Test
    public void shouldDeletePet() {
        System.out.println("\n=== Running: shouldDeletePet ===");

        // // Step 1: ensure pet exists
        // String createBody = """
        // {
        //   "id": 9001,
        //   "name": "test-subscription",
        //   "status": "available"
        // }
        // """;

        petClient.createPet(pet);

        // delete request routed through client layer
        Response response = petClient.deletePet(9001);

        response.then().log().all();

        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.getBody().asString());

        assertEquals(200, response.statusCode());
    }


}