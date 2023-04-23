package ru.javaops.topjava2.web.dish;

import org.mapstruct.Mapper;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.to.DishTo;

import java.util.List;

@Mapper
public interface DishMapper {
    DishTo toDto(Dish dish);

    Dish toModel(DishTo dish);

    List<DishTo> toListDto(List<Dish> dishes);
}
