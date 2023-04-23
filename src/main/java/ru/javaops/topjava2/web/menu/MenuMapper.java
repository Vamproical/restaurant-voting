package ru.javaops.topjava2.web.menu;

import org.mapstruct.Mapper;
import ru.javaops.topjava2.model.Menu;
import ru.javaops.topjava2.to.MenuTo;

import java.util.List;

@Mapper
public interface MenuMapper {
    MenuTo toDto(Menu menu);

    List<MenuTo> toListDto(List<Menu> menus);
}
