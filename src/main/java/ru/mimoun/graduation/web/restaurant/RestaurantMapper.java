package ru.mimoun.graduation.web.restaurant;

import org.mapstruct.Mapper;
import ru.mimoun.graduation.model.Restaurant;
import ru.mimoun.graduation.to.RestaurantTo;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    Restaurant toModel(RestaurantTo restaurant);

    RestaurantTo toDto(Restaurant restaurant);

    List<RestaurantTo> toListDto(List<Restaurant> restaurants);
}
