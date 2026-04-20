package api.client;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import api.utils.Config;

public class AuthClient {

    private static final String BASE_URL = Config.getNotesBaseUrl();

    // Logs in and returns authentication token
    public static String getAuthToken() {

        // same login data used in Swagger UI
        Map<String,String> credentials = new HashMap<>();

        credentials.put("email", "noah123@test.com");
        credentials.put("password", "Test123!");

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(credentials)
                .when()
                        .post(BASE_URL + "/users/login");

        // helpful for debugging if login ever fails
        response.then().log().all();

        // extract token from JSON response
        return response
                .jsonPath()
                .getString("data.token");
    }

    // reusable POST with auth token
    public static Response postWithAuth(String endpoint, Object body) {
        return given()
                .header("x-auth-token", getAuthToken())
                .contentType("application/json")
                .body(body)
        .when()
                .post(BASE_URL + endpoint);
    }

    // reusable GET with auth token
    public static Response getWithAuth(String endpoint) {
        return given()
                .header("x-auth-token", getAuthToken())
        .when()
                .get(BASE_URL + endpoint);
    }

    // reusable PUT with auth token
    public static Response putWithAuth(String endpoint, Object body) {
        return given()
                .header("x-auth-token", getAuthToken())
                .contentType("application/json")
                .body(body)
        .when()
                .put(BASE_URL + endpoint);
    }

    // reusable DELETE with auth token
    public static Response deleteWithAuth(String endpoint) {
        return given()
                .header("x-auth-token", getAuthToken())
        .when()
                .delete(BASE_URL + endpoint);
    }
}
