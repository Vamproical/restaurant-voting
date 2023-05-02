package ru.javaops.topjava2.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Value
public class MenuTo extends BaseTo {
    RestaurantTo restaurant;
    List<DishTo> dishes;
    LocalDate date;
}
