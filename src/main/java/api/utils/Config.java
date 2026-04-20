package api.utils;
public class Config {

    /*
     PREVIOUS APPROACH (Week 3 Day 1–3):
     We set RestAssured.baseURI globally inside setup().
     This works when the framework only talks to ONE API.

     Problem:
     Now we need to test MULTIPLE APIs (Petstore + Auth Notes API).
     If baseURI is hardcoded, we must constantly comment/uncomment URLs.

     BETTER APPROACH:
     Store base URLs as reusable methods so tests and clients
     can choose which service to call.

     This makes the framework scalable for real microservices projects.
    */

    public static void setup() {
        // OLD way (kept for reference)
        // RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    // Petstore API (used in Week 3 Day 1–3 CRUD tests)
    public static String getPetstoreBaseUrl() {
        return "https://petstore.swagger.io/v2";
    }

    // Authenticated Notes API (used in Week 3 Day 4 request chaining)
    public static String getNotesBaseUrl() {
        return "https://practice.expandtesting.com/notes/api";
    }

    /*
     DAY 5 PREPARATION (do NOT delete existing methods above)
     -------------------------------------------------------
     This allows switching environments using command line:

     mvn test -Denv=notes
     mvn test -Denv=petstore

     Default = notes API so Day 4 tests continue working.
    */

    public static String getBaseUrl() {

        String env = System.getProperty("env");

        if (env == null) {
            return getNotesBaseUrl(); // default for Day 4 auth tests
        }

        switch (env.toLowerCase()) {

            case "petstore":
                return getPetstoreBaseUrl();

            case "notes":
                return getNotesBaseUrl();

            default:
                return getNotesBaseUrl();
        }
    }
/*
================ INDUSTRY VERSION Calling properties file (COMMENTED FOR LEARNING) ================
This version loads environment-specific config files automatically.
It runs ONCE when Config class is first used anywhere in the tests.

Example usage from terminal:

mvn clean test -Denv=dev
mvn clean test -Denv=qa
mvn clean test -Denv=staging

How it works:
1. System.getProperty("env", "dev") reads env from terminal
2. Builds file name like config-dev.properties
3. Loads file from src/main/resources
4. Stores values in Properties object
5. getPetstoreBaseUrl() and getNotesBaseUrl() read from file

Nothing runs automatically until Config class is referenced
(example: Config.getPetstoreBaseUrl())
==========================================================================

import java.io.InputStream;
import java.util.Properties;

 Properties is a built-in Java class — you do NOT create it yourself.
private static Properties properties = new Properties();

static {
try {
String env = System.getProperty("env", "qa"); // default to "qa" if env not provided

String fileName = "config-" + env + ".properties";

InputStream input =
        Config.class.getClassLoader().getResourceAsStream(fileName);

if (input == null) {
    throw new RuntimeException("Could not find " + fileName);
}

properties.load(input);


public static String getPetstoreBaseUrl_fromFile() {
return properties.getProperty("petstore.url");
}

public static String getNotesBaseUrl_fromFile() {
return properties.getProperty("notes.url");
}

==========================================================================
*/
}
