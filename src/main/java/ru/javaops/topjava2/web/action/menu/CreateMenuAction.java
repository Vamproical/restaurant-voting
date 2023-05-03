package ru.javaops.topjava2.web.action.menu;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Menu;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.repository.MenuRepository;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.to.CreateMenuTo;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CreateMenuAction {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository repository;

    @Transactional
    public Menu execute(@NotNull CreateMenuTo menuTo) {
        Restaurant restaurant = restaurantRepository.getExisted(menuTo.getRestaurantId());
        LocalDate date = menuTo.getMenuDate() == null ? LocalDate.now() : menuTo.getMenuDate();

        return repository.save(new Menu(null, restaurant, menuTo.getDishes(), date));
    }
}
