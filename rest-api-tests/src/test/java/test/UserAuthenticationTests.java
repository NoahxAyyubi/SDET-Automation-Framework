package test;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import api.client.AuthClient;

public class UserAuthenticationTests {

    @Test
    public void shouldCompleteFullNoteWorkflow() {

        // Step 1: create request body
        Map<String,String> note = new HashMap<>();
        note.put("title", "Automation Note");
        note.put("description", "created from test framework");
        note.put("category", "Home");

        // Step 2: CREATE note
        Response createResponse = AuthClient.postWithAuth(
                "/notes",
                note
        );

        createResponse.then().log().all();
        assertEquals(200, createResponse.statusCode());

        String noteId = createResponse.jsonPath().getString("data.id");
        System.out.println("Created note ID: " + noteId);

        // Step 3: GET note
        Response getResponse = AuthClient.getWithAuth(
                "/notes/" + noteId
        );

        getResponse.then().log().all();
        assertEquals(200, getResponse.statusCode());

        // Step 4: UPDATE note
        note.put("completed", "false");
        note.put("title", "Updated Automation Note");

        Response updateResponse = AuthClient.putWithAuth(
                "/notes/" + noteId,
                note
        );

        updateResponse.then().log().all();
        assertEquals(200, updateResponse.statusCode());

        // Step 5: DELETE note
        Response deleteResponse = AuthClient.deleteWithAuth(
                "/notes/" + noteId
        );

        deleteResponse.then().log().all();
        assertEquals(200, deleteResponse.statusCode());
    }
}
