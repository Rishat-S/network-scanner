package ru.lsz.bing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Bing Wallpaper
 */
public class BingApiResponse {
    static ObjectMapper mapper = new ObjectMapper();
    public static final String URI = "https://bing.biturl.top/";

    public static void main(String[] args) {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveImageToFile(String url, String pathToPastImage) throws MalformedURLException {
        try (InputStream in = new URL(url).openStream()) {
            Files.copy(in, Paths.get(pathToPastImage), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
