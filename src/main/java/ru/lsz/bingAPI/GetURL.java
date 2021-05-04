package ru.lsz.bingAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

/**
 * Bing Wallpaper
 */
public class GetURL {
    static ObjectMapper mapper = new ObjectMapper();
    public static final String URI = "https://bing.biturl.top/";

    public static String getURL() throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(URI);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            ApiBingWallPaper apiBingWallPaper = mapper.readValue(response.getEntity().getContent(), ApiBingWallPaper.class);

            return apiBingWallPaper.getUrl();

        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new Exception("Wrong request");
    }

}
