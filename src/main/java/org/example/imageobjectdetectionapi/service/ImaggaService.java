package org.example.imageobjectdetectionapi.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.imageobjectdetectionapi.exception.ImaggaBadRequestException;
import org.example.imageobjectdetectionapi.model.ImageRequest;
import org.example.imageobjectdetectionapi.model.ImaggaWebResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImaggaService {

    @Value("${imaggaApiKey}")
    private String IMAGGA_KEY;

    @Value("${imaggaApiSecret}")
    private String IMAGGA_SECRET;

    // TODO: Refactor to use RestTemplate/WebClient instead
    public ImaggaWebResponse getObjectDetection(ImageRequest imageRequest) {
        try {
            String url = "https://api.imagga.com/v2/tags?image_url=" + imageRequest.getImageUrl() + "&limit=10";
            URL urlObject = new URL(url);

            String credentialsToEncode = IMAGGA_KEY + ":" + IMAGGA_SECRET;
            String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

            connection.setRequestProperty("Authorization", "Basic " + basicAuth);

            BufferedReader connectionInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String jsonResponse = connectionInput.readLine();

            connectionInput.close();

            return new Gson().fromJson(jsonResponse, ImaggaWebResponse.class);
        } catch (IOException e) {
            if (e.getMessage().contains("Server returned HTTP response code: 400")) {
                throw new ImaggaBadRequestException("Error occurred when sending request to Imagga -- " + e.getMessage());
            }
            log.error("Something happened when attempting to parse JSON response body from Imagga");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error while reading JSON response from Imagga -- " + e.getMessage());
        } catch (Exception e) {
            log.error("Something went wrong getting object tags from Imagga");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Tag retrieval failed -- " + e.getMessage());
        }
    }

}
