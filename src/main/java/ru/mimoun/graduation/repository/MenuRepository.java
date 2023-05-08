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
    @Query("select m from Menu m where m.date = :date order by m.date desc, m.restaurant.name asc")
    List<Menu> findAllByDate(@NotNull @Param("date") LocalDate date);

    @Query("select m from Menu m where m.restaurant.id = :restaurantId order by m.date desc, m.restaurant.name asc")
    List<Menu> findAllByRestaurant(@NotNull @Param("restaurantId") Integer restaurantId);
}
