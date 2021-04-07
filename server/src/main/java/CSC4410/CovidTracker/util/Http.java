package CSC4410.CovidTracker.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Http {
    private Http() {}

    private static HttpClient client = null;

    public static HttpClient getClient() {
        if (client == null)
            client = HttpClient.newHttpClient();
        return client;
    }

    public static String get(String uri) throws IOException, InterruptedException {
        HttpClient client = getClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

}
