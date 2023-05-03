package ru.javaops.topjava2.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class MenuTo extends BaseTo {
    RestaurantTo restaurant;
    List<DishTo> dishes;
    LocalDate date;

    public MenuTo(Integer id, RestaurantTo restaurant, List<DishTo> dishes, LocalDate date) {
        super(id);
        this.restaurant = restaurant;
        this.dishes = dishes;
        this.date = date;
    }
}
