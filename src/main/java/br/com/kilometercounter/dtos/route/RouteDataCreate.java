package br.com.kilometercounter.dtos.route;

import br.com.kilometercounter.domain.Client;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record RouteDataCreate(
        @NotBlank
        Client originClient,
        @NotBlank
        Client destinationClient,
        @NotBlank
        Double distance,
        @NotBlank
        LocalDate routeDate
) {
}
