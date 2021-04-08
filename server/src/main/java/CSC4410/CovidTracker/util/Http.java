package CSC4410.CovidTracker.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Utility methods for working with HTTP requests.
 */
public class Http {
    private Http() {}

    private static HttpClient client = null;

    /**
     * @return The default HTTP client. If it does not yet exist, it will be
     * created.
     */
    public static HttpClient getClient() {
        if (client == null)
            client = HttpClient.newHttpClient();
        return client;
    }

    /**
     * Sends a GET request to a given URI.
     * @param uri The URI to request.
     * @return The body of the HTTP response.
     * @throws IOException
     * @throws InterruptedException
     */
    public static String get(String uri) throws IOException, InterruptedException {
        HttpClient client = getClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

}
