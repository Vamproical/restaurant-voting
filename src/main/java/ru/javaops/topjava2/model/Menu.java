package ru.javaops.topjava2.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.javaops.topjava2.HasId;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "menu", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"restaraunt_id", "date"},
                          name = "unique_restaurant_menu_by_date_idx")
})
public class Menu extends BaseEntity implements HasId, Serializable {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaraunt_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @NotEmpty
    @ElementCollection(targetClass = Dish.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @CollectionTable(name = "menu_dishes", joinColumns = @JoinColumn(name = "menu_id"),
                     uniqueConstraints = @UniqueConstraint(columnNames = {"menu_id", "dish_name"},
                                                           name = "unique_dish_on_menu_idx"))
    private List<Dish> dishes;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public Menu(Integer id, Restaurant restaurant, List<Dish> dishes, @Nullable LocalDate date) {
        super(id);
        this.restaurant = restaurant;
        this.dishes = dishes;
        this.date = date;
    }
}


