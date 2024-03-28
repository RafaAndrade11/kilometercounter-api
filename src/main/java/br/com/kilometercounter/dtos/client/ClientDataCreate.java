package br.com.kilometercounter.dtos.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientDataCreate(
        @NotBlank String name,
        @NotNull Long cep
) {
}
