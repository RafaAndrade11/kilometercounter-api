package br.com.kilometercounter.dtos.client;

import jakarta.validation.constraints.NotBlank;

public record ClientDataCreate(
        @NotBlank String name,
        @NotBlank String address
) {
}
