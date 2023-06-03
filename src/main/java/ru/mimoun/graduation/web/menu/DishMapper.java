package ru.mimoun.graduation.web.menu;

import org.mapstruct.Mapper;
import ru.mimoun.graduation.model.Dish;
import ru.mimoun.graduation.to.DishTo;

import java.util.List;

@Mapper
public interface DishMapper {
    List<Dish> toDishList(List<DishTo> dishTos);
}
