package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Utils {
    public static String getUrl(String nasaUrl) {
        CloseableHttpClient client = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        HttpGet request = new HttpGet(nasaUrl);

        try {
            CloseableHttpResponse response = client.execute(request);
            NasaAnswer answer = mapper.readValue(response.getEntity().getContent(), NasaAnswer.class);
            return answer.getUrl();
        } catch (IOException e) {
            System.out.println("Error");
        }
        return "";
    }
}
