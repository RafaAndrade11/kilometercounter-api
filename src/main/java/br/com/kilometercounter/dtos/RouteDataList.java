package br.com.kilometercounter.dtos;

import br.com.kilometercounter.domain.Client;
import br.com.kilometercounter.domain.Route;

public record RouteDataList(Long id, Client destinationClient, Client originClient, Double distance) {
    public RouteDataList(Route route) {
        this(route.getId(),route.getOriginClient(), route.getDestinationClient(), route.getDistance());
    }
}
