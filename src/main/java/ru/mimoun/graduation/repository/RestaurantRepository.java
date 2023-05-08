package ru.mimoun.graduation.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.mimoun.graduation.model.Restaurant;

import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    List<Restaurant> findAllByNameContainsIgnoreCase(String name);
}
