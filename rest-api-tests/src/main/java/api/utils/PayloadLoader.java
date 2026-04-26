package api.utils;

public class PayloadLoader {

    /*
     * ================= WORKBOOK FLOW =================
     * STEP 1
     * Create folder:
     * src/test/resources/payloads
     *
     * STEP 2
     * Create JSON files inside payloads folder
     * Example:
     * createSubscriptionValid.json
     * createSubscriptionMissingName.json
     * createSubscriptionInvalidStatus.json
     *
     * STEP 3
     * Call this loader inside test class:
     * String body = PayloadLoader.load("createSubscriptionValid.json");
     *
     * STEP 4
     * Send request using PetClient:
     * Response response = petClient.createPet(body);
     *
     * STEP 5
     * Validate response in test using assertions
     *
     * NOTE:
     * Payload = test data only
     * Client = sends request
     * Test = validates behavior
     * ================================================
     */

    /*
     * Purpose:
     * Reads JSON files from src/test/resources/payloads
     * Returns the file content as a String
     *
     * Example usage in test:
     * String body = PayloadLoader.load("createSubscriptionValid.json");
     */

    public static String load(String fileName) {

        try {
            // TODO workbook:
            // confirm your payload file exists inside:
            // src/test/resources/payloads
            // example call:
            // PayloadLoader.load("createSubscriptionValid.json");

            // path where payload files live
            String path = "src/test/resources/payloads/" + fileName;

            // converts JSON file into raw request body string
            // this string will be passed into API request
            return new String(java.nio.file.Files.readAllBytes(
                    java.nio.file.Paths.get(path)
            ));

        } catch (Exception e) {
            throw new RuntimeException("Failed to load payload file: " + fileName);
        }
    }
}
