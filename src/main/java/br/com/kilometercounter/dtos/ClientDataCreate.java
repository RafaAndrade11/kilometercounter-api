package br.com.kilometercounter.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClientDataCreate(
        @NotBlank String name,
        @NotBlank String address
) {
}
