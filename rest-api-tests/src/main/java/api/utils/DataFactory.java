package api.utils;

public class DataFactory {
    

    public static String createSubscriptionBody(
            long id,
            String name,
            String status
    ) {

        return """
        {
          "id": %d,
          "name": "%s",
          "status": "%s"
        }
        """.formatted(id, name, status);
    }
// we can use model classes like Pet to create bodies too, but this is an example of how we can use a factory to create raw JSON strings if needed (like for PayloadLoader)

}
