package ru.javaops.topjava2.to;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.javaops.topjava2.model.Dish;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuTo {
    @NotNull
    Integer restaurantId;

    @NotNull
    List<Dish> dishes;

    LocalDate menuDate;
}
