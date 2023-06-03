package ru.mimoun.graduation.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;
import ru.mimoun.graduation.model.Restaurant;

import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    List<Restaurant> findAllByNameContainsIgnoreCase(@NotNull String name);
}
