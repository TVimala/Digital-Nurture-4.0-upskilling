import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTP {
    public static void main(String[] args) throws Exception {
        // Create HttpClient instance
        HttpClient client = HttpClient.newHttpClient();

        // Build HTTP GET request to GitHub API (example repo)
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/repos/openai/gpt-3"))
                .GET()
                .build();

        // Send request and get response as String
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print HTTP status code
        System.out.println("Response status code: " + response.statusCode());

        // Print response body (JSON text)
        System.out.println("Response body:");
        System.out.println(response.body());
    }
}
