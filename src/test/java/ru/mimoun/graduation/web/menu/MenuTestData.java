package ru.mimoun.graduation.web.menu;

import ru.mimoun.graduation.model.Dish;
import ru.mimoun.graduation.model.Menu;
import ru.mimoun.graduation.to.DishTo;
import ru.mimoun.graduation.to.MenuTo;
import ru.mimoun.graduation.web.MatcherFactory;
import ru.mimoun.graduation.web.restaurant.RestaurantTestData;

import java.time.LocalDate;
import java.util.List;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingEqualsComparator(Menu.class);
    public static final MatcherFactory.Matcher<MenuTo> MENU_TO_MATCHER = MatcherFactory.usingEqualsComparator(MenuTo.class);
    protected static final int MENU_ID = 1;

    public static final Menu menu = new Menu(1, RestaurantTestData.restaurant, List.of(new Dish("Delicious dish", 150),
                                                                                       new Dish("Hamburger", 200)),
                                             LocalDate.now());

    public static final MenuTo menuTo = new MenuTo(1, RestaurantTestData.restaurantTo, List.of(new DishTo("Delicious dish", 150),
                                                                                               new DishTo("Hamburger", 200)),
                                                   LocalDate.now());

    public static final Menu menu2 = new Menu(2, RestaurantTestData.restaurant2, List.of(new Dish("Not visible dish", 10)),
                                              LocalDate.of(2023, 5, 1));

    public static final MenuTo menuTo2 = new MenuTo(2, RestaurantTestData.restaurantTo2, List.of(new DishTo("Not visible dish", 10)),
                                                    LocalDate.of(2023, 5, 1));
}
