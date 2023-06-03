package ru.mimoun.graduation.to;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuTo {
    int restaurantId;

    @NotNull
    List<DishTo> dishes;

    LocalDate menuDate;
}
