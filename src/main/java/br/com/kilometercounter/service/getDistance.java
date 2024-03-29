package br.com.kilometercounter.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class getDistance {

    private static final String API_KEY = "AIzaSyAwvE64TUa4HxK7B1mOeyKZtOB4cr6uiJw";

    public static double getData(String source, String destination) throws IOException, InterruptedException {
        var url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + source + "&destinations=" + destination + "&key=" + API_KEY;
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

        // parsing distance and convert String to Double
        String distanceString = jsonObject.getAsJsonArray("rows")
                .get(0).getAsJsonObject()
                .getAsJsonArray("elements")
                .get(0).getAsJsonObject()
                .getAsJsonObject("distance")
                .get("text").getAsString();

        distanceString = distanceString.replace(" km", "");

        System.out.println(distanceString);
        return Double.parseDouble(distanceString);

    }
}

