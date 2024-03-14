package br.com.kilometercounter.dtos;

import br.com.kilometercounter.domain.Client;
import jakarta.validation.constraints.NotNull;

public record RouteDataUpdate(@NotNull Long id,
                              Client originClient,
                              Client destinationClient,
                              Double distance) {

}
