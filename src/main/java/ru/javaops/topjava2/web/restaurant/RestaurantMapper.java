package ru.javaops.topjava2.web.restaurant;

import org.mapstruct.Mapper;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.to.RestaurantTo;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    RestaurantTo toDto(Restaurant restaurant);

    List<RestaurantTo> toListDto(List<Restaurant> restaurants);
}
