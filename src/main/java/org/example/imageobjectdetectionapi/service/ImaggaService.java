package org.example.imageobjectdetectionapi.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.imageobjectdetectionapi.dto.ImageRequest;
import org.example.imageobjectdetectionapi.dto.ImaggaRO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImaggaService {

	private final String URI = "https://api.imagga.com/v2";

    // TODO: Refactor to use RestTemplate/WebClient instead
	public ImaggaRO getObjectDetection(ImageRequest imageRequest) {
		try {
			String credentialsToEncode = "acc_343f1665680cff0" + ":" + "30e0f9ea940d10a99d4fab53a1870d58";
			String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));

			String url = URI + "/tags?image_url=" + imageRequest.getImageUrl() + "&limit=10";
			URL urlObject = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

			connection.setRequestProperty("Authorization", "Basic " + basicAuth);

			BufferedReader connectionInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String jsonResponse = connectionInput.readLine();

			connectionInput.close();

			return new Gson().fromJson(jsonResponse, ImaggaRO.class);
		}
		catch (Exception e) {
			log.error("Something went wrong getting object tags from Imagga");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Tag retrieval failed -- " + e.getMessage());
		}
	}

}
