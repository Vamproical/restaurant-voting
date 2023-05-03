package ru.javaops.topjava2.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Restaurant;

import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    List<Restaurant> findAllByNameContainsIgnoreCase(String name);
}
