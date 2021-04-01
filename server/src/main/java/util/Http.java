Htt
package util;

import java.net.http.HttpClient;

public class Http {
    private static HttpClient client = null;

    private Http() {}

    public static HttpClient getClient() {
        if (client == null)
            client = HttpClient.newHttpClient();

        return client;
    }

}