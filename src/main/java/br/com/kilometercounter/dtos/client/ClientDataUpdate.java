package br.com.kilometercounter.dtos.client;

import jakarta.validation.constraints.NotNull;

public record ClientDataUpdate(
        @NotNull Long id,
        String name,
        String address
) {
}
