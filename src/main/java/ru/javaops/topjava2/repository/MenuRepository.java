package ru.javaops.topjava2.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menu> {
    @EntityGraph(attributePaths = {"restaurant"})
    @Query("select m from Menu m where m.date = :date order by m.date desc, m.restaurant.name asc")
    List<Menu> findAllByDate(@NotNull @Param("date") LocalDate date);

}
