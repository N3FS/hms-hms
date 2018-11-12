package org.moss.discord.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.javacord.api.DiscordApi;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class BStatsUtil {

    /**
     * The mapper used to map json objects.
     */
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * The http client which is used to execute rest calls.
     */
    private static final OkHttpClient client = new OkHttpClient.Builder().build();

    /**
     * The Discord api instance.
     */
    private final DiscordApi api;

    /**
     * Creates a new bStats util.
     *
     * @param api A Discord api instance.
     */
    public BStatsUtil(DiscordApi api) {
        this.api = api;
    }

    /**
     * Executes a blocking GET request to the given url.
     *
     * @param url The url.
     * @return A json node.
     * @throws IOException If something went wrong.
     */
    public JsonNode makeRequest(String url) throws IOException {
        System.out.println("Request: " + url);
        Request request = new Request.Builder()
                .url(url)
                .build();
        return mapper.readTree(Objects.requireNonNull(client.newCall(request).execute().body()).string());
    }

}
