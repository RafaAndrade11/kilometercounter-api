package br.com.kilometercounter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapService {

    @Value("${map.api.key}")
    private String mapApiKey;

    public String geocodeAddress(String address) {
        String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json";
        String apiKeyParam = "key=" + mapApiKey;
        String addressParam = "address=" + address;

        String url = String.format("%s?%s&%s", apiUrl, addressParam, apiKeyParam);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        return response;
    }

}
