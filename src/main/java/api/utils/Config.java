package api.utils;
import io.restassured.RestAssured;
public class Config {
    public static void setup() {
        // Set up any global configurations for RestAssured here
        // For example, you can set the base URI if all tests use the same API endpoint
         RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }   
    
}
