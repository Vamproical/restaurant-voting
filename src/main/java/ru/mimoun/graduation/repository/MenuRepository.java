package ru.mimoun.graduation.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.mimoun.graduation.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menu> {
    @Query("SELECT m FROM Menu m WHERE m.date = :date ORDER BY m.date DESC, m.restaurant.name ASC")
    List<Menu> findAllByDate(@NotNull @Param("date") LocalDate date);

    @Query("SELECT m FROM Menu m WHERE  m.restaurant.id = :restaurantId AND m.date=:date ORDER BY m.date DESC, m.restaurant.name ASC")
    List<Menu> findAllByRestaurant(@Param("restaurantId") int restaurantId, @NotNull @Param("date") LocalDate date);
}
