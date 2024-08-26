package org.example.imageobjectdetectionapi.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.example.imageobjectdetectionapi.dto.ImaggaRO;
import org.example.imageobjectdetectionapi.dto.ImageRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImaggaService {

    private final String URI = "https://api.imagga.com/v2";
    @Value("${imaggaApiKey}")
    private String imaggaApiKey;
    @Value("${imaggaApiSecret}")
    private String imaggaApiSecret;
    @Value("${imaggaAuthHeader}")
    private String imaggaAuthHeader;

    private RestTemplate restTemplate;

    @Autowired
    public ImaggaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ImaggaRO getObjectDetection(ImageRequest imageRequest) {
        try {
            String credentialsToEncode = "acc_343f1665680cff0" + ":" + "30e0f9ea940d10a99d4fab53a1870d58";
            String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));

            String image_url = "https://imagga.com/static/images/tagging/wind-farm-538576_640.jpg";

            String url = URI + "/tags?image_url=" + image_url + "&limit=10";
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

            connection.setRequestProperty("Authorization", "Basic " + basicAuth);

            BufferedReader connectionInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String jsonResponse = connectionInput.readLine();

            connectionInput.close();

            return new Gson().fromJson(jsonResponse, ImaggaRO.class);
        } catch (Exception e) {
            log.error("Something went wrong getting object tags from Imagga");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Tag retrieval failed -- " + e.getMessage());
        }
    }

}
