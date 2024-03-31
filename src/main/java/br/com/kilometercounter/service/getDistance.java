package br.com.kilometercounter.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class getDistance {

    private static final String API_KEY = "AIzaSyAwvE64TUa4HxK7B1mOeyKZtOB4cr6uiJw";

    public static double getData(Long source, Long destination) throws IOException, InterruptedException {
        var url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + source + "&destinations=" + destination + "&key=" + API_KEY;
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

        if (!jsonObject.has("rows")) {
            System.out.println("Chave 'rows' não encontrada no JSON");
            return 0.0; // Ou outro valor padrão, dependendo do seu caso
        }

        JsonArray rowsArray = jsonObject.getAsJsonArray("rows");
        if (rowsArray.size() == 0) {
            System.out.println("Array 'rows' vazio no JSON");
            return 0.0; // Ou outro valor padrão, dependendo do seu caso
        }

        JsonObject firstRow = rowsArray.get(0).getAsJsonObject();
        // Verificar se a chave "elements" existe no JSON
        if (!firstRow.has("elements")) {
            System.out.println("Chave 'elements' não encontrada no JSON");
            return 0.0; // Ou outro valor padrão, dependendo do seu caso
        }

        JsonArray elementsArray = firstRow.getAsJsonArray("elements");
        if (elementsArray.size() == 0) {
            System.out.println("Array 'elements' vazio no JSON");
            return 0.0; // Ou outro valor padrão, dependendo do seu caso
        }

        JsonObject firstElement = elementsArray.get(0).getAsJsonObject();
        // Verificar se a chave "distance" existe no JSON
        if (!firstElement.has("distance")) {
            System.out.println("Chave 'distance' não encontrada no JSON");
            return 0.0; // Ou outro valor padrão, dependendo do seu caso
        }

        JsonObject distanceObject = firstElement.getAsJsonObject("distance");
        // Verificar se a chave "text" existe no JSON
        if (!distanceObject.has("text")) {
            System.out.println("Chave 'text' não encontrada no JSON");
            return 0.0; // Ou outro valor padrão, dependendo do seu caso
        }

        String distanceString = distanceObject.get("text").getAsString();
        distanceString = distanceString.replace(" km", "");

        System.out.println(distanceString);
        return Double.parseDouble(distanceString);

    }
}

