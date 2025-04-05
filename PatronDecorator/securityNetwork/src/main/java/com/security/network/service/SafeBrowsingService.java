package com.security.network.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;

@Service
public class SafeBrowsingService {

    @Value("${google.safebrowsing.api_key}")
    private String apiKey;

    public boolean esMaliciosa(String url) {
        try {
            String requestUrl = "https://safebrowsing.googleapis.com/v4/threatMatches:find?key=" + apiKey;
            HttpURLConnection connection = (HttpURLConnection) URI.create(requestUrl).toURL().openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            String jsonInput = """
                {
                  "client": {
                    "clientId": "yourcompany",
                    "clientVersion": "1.0"
                  },
                  "threatInfo": {
                    "threatTypes": ["MALWARE", "SOCIAL_ENGINEERING"],
                    "platformTypes": ["ANY_PLATFORM"],
                    "threatEntryTypes": ["URL"],
                    "threatEntries": [
                      {"url": "%s"}
                    ]
                  }
                }
                """.formatted(url);

            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonInput.getBytes());
                os.flush();
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode response = mapper.readTree(connection.getInputStream());

            return response != null && response.has("matches");
        } catch (Exception e) {
            return false;
        }
    }
}
