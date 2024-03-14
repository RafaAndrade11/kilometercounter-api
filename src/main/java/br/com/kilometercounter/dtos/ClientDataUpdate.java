package br.com.kilometercounter.dtos;

import jakarta.validation.constraints.NotNull;

public record ClientDataUpdate(
        @NotNull Long id,
        String name,
        String address
) {
}
