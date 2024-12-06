import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JavaClient {
    private static final String API_URL = "http://localhost:5000";
    private static final HttpClient client = HttpClient.newHttpClient();

    public static String processMetadata(String metadata) throws Exception {
        String jsonInput = String.format("{\"metadata\": \"%s\"}", metadata);
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL + "/process"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonInput))
            .build();

        HttpResponse<String> response = client.send(request, 
            HttpResponse.BodyHandlers.ofString());
        
        return response.body();
    }

    public static String retrieveMetadata(String category) throws Exception {
        String url = category != null 
            ? API_URL + "/retrieve?category=" + category
            : API_URL + "/retrieve";
            
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());
            
        return response.body();
    }

    public static void main(String[] args) {
        try {
            String metadata = """
                <meta name="title" content="Test Book">
                <meta name="author" content="Test Author">
                <meta name="isbn" content="123-456-789">
                <meta name="source_site" content="example.com">
                """;

            // Process metadata
            String result = processMetadata(metadata);
            System.out.println("Processed metadata: " + result);

            // Retrieve all metadata
            String stored = retrieveMetadata(null);
            System.out.println("All stored metadata: " + stored);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
