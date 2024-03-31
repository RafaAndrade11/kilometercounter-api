package br.com.kilometercounter.dtos.route;

import br.com.kilometercounter.domain.Client;
import br.com.kilometercounter.service.getDistance;
import jakarta.validation.constraints.NotBlank;

import java.io.IOException;
import java.time.LocalDate;

public record RouteDataCreate(

        Client originClient,
        Client destinationClient,
        Double distance,
        LocalDate routeDate
) {
        public double getDistance() {
                try {
                        Long originAddress = originClient.getCep();
                        Long destinationAddress = destinationClient.getCep();

                        return getDistance.getData(originAddress, destinationAddress);
                } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        return 0.0;
                }
        }
}

