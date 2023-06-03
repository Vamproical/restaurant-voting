package ru.mimoun.graduation.service;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mimoun.graduation.model.Menu;
import ru.mimoun.graduation.model.Restaurant;
import ru.mimoun.graduation.repository.MenuRepository;
import ru.mimoun.graduation.repository.RestaurantRepository;
import ru.mimoun.graduation.to.CreateMenuTo;
import ru.mimoun.graduation.to.UpdateMenuTo;
import ru.mimoun.graduation.web.menu.DishMapper;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository repository;
    private final DishMapper dishMapper;

    @Transactional
    public Menu save(@NotNull CreateMenuTo menuTo) {
        Restaurant restaurant = restaurantRepository.getExisted(menuTo.getRestaurantId());
        LocalDate date = menuTo.getMenuDate() == null ? LocalDate.now() : menuTo.getMenuDate();

        return repository.save(new Menu(null, restaurant, dishMapper.toDishList(menuTo.getDishes()), date));
    }

    @Transactional
    public void update(@NonNull UpdateMenuTo menuTo, int menuId) {
        Restaurant restaurant = restaurantRepository.getExisted(menuTo.restaurantId());
        Menu menu = repository.getExisted(menuId);

        menu.setRestaurant(restaurant);
        menu.setDishes(dishMapper.toDishList(menuTo.dishes()));

        repository.save(menu);
    }
}
