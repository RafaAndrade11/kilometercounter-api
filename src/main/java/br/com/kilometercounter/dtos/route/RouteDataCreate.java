package br.com.kilometercounter.dtos.route;

import br.com.kilometercounter.domain.Client;

import java.time.LocalDate;

public record RouteDataCreate(

        Client originClient,
        Client destinationClient,
        Double distance,
        LocalDate routeDate
) {
}

