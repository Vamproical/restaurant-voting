package ru.mimoun.graduation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "menu", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"menu_date", "restaraunt_id"},
                          name = "unique_restaurant_menu_by_date_idx")
})
public class Menu extends BaseEntity implements Serializable {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaraunt_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @NotEmpty
    @ElementCollection(targetClass = Dish.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @CollectionTable(name = "dish", joinColumns = @JoinColumn(name = "menu_id"),
                     uniqueConstraints = @UniqueConstraint(columnNames = {"menu_id", "dish_name"},
                                                           name = "unique_dish_on_menu_idx"))
    private List<Dish> dishes;

    @Column(name = "menu_date", nullable = false)
    private LocalDate date;

    public Menu(Integer id, @NotNull Restaurant restaurant, List<Dish> dishes, @NotNull LocalDate date) {
        super(id);
        this.restaurant = restaurant;
        this.dishes = dishes;
        this.date = date;
    }
}


