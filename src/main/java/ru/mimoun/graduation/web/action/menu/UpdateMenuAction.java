package ru.mimoun.graduation.web.action.menu;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.mimoun.graduation.model.Menu;
import ru.mimoun.graduation.model.Restaurant;
import ru.mimoun.graduation.repository.MenuRepository;
import ru.mimoun.graduation.repository.RestaurantRepository;
import ru.mimoun.graduation.to.UpdateMenuTo;
import ru.mimoun.graduation.web.menu.MenuMapper;

@Component
@RequiredArgsConstructor
public class UpdateMenuAction {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository repository;
    private final MenuMapper mapper;

    @Transactional
    public Menu execute(@NonNull UpdateMenuTo menuTo, @NonNull Integer menuId) {
        Restaurant restaurant = restaurantRepository.getExisted(menuTo.restaurantId());
        Menu menu = repository.getExisted(menuId);

        menu.setRestaurant(restaurant);
        menu.setDishes(mapper.toDishList(menuTo.dishes()));

        return repository.save(menu);
    }
}
