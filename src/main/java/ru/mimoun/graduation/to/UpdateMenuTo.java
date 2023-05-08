package ru.mimoun.graduation.to;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateMenuTo(@NotNull Integer restaurantId, @NotNull List<DishTo> dishes) {
}
