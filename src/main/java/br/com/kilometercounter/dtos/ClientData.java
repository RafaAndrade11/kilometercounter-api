package br.com.kilometercounter.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClientData(
        @NotBlank String name,
        @NotBlank String address
) {
}
