package ru.babim.template.helloworld.hellomicroservice.dto.name;

import jakarta.validation.constraints.NotBlank;

public record NameDto(@NotBlank String firstName,
                      @NotBlank String lastName) {
}
