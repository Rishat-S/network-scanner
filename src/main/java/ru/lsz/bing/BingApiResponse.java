package ru.lsz.bing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;
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

    public static String getHttpClient() {
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

        return null;

    }

    public static void saveImageToFile(String url, String compName) {
        final String SCREENSAVER_DOMINANT_SCREEN_SAVER_JPG = "\\C$\\screensaver\\DominantScreenSaver.jpg";
        String[] name = compName.split("\\.");
        String pathToPastImage = "\\\\" + name[0] + SCREENSAVER_DOMINANT_SCREEN_SAVER_JPG;

        try (InputStream in = new URL(url).openStream()) {

            Files.copy(in, Paths.get(pathToPastImage), StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
