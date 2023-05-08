package ru.mimoun.graduation.to;

import jakarta.validation.constraints.NotNull;

public record DishTo(@NotNull String name, @NotNull Integer price) {
}
