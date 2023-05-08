package ru.mimoun.graduation.web.menu;

import org.mapstruct.Mapper;
import ru.mimoun.graduation.model.Dish;
import ru.mimoun.graduation.model.Menu;
import ru.mimoun.graduation.to.DishTo;
import ru.mimoun.graduation.to.MenuTo;

import java.util.List;

@Mapper
public interface MenuMapper {
    MenuTo toDto(Menu menu);

    List<MenuTo> toListDto(List<Menu> menus);

    List<Dish> toDishList(List<DishTo> dishTos);
}
