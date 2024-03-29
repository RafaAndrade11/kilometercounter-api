package br.com.kilometercounter.apitests;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class getDistance {
    private static final String API_KEY="AIzaSyAwvE64TUa4HxK7B1mOeyKZtOB4cr6uiJw";
    public static float [][] distance;
    public static float [][] times;

    public static void getData(String source, String destination) throws IOException, InterruptedException {
        var url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+source+"&destinations="+destination+"&key="+API_KEY;
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        System.out.println(response);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String source = "26030830";
        String destination = "26040100";
        getData(source, destination);

    }
}
