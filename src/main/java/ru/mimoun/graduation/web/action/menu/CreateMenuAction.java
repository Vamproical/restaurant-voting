package ru.mimoun.graduation.web.action.menu;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.mimoun.graduation.model.Menu;
import ru.mimoun.graduation.model.Restaurant;
import ru.mimoun.graduation.repository.MenuRepository;
import ru.mimoun.graduation.repository.RestaurantRepository;
import ru.mimoun.graduation.to.CreateMenuTo;

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
