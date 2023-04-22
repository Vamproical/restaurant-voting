package ru.javaops.topjava2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "menu", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"restaraunt_id", "create_date"}, name = "unique_restaurant_menu_by_date_idx")
})
public class Menu extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaraunt_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @NotEmpty
    @ElementCollection
    @JoinColumn(name = "dish_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @CollectionTable(name = "menu_item", joinColumns = @JoinColumn(name = "menu_id"),
                     uniqueConstraints = @UniqueConstraint(columnNames = {"menu_id", "dish_name"}))
    private List<Dish> dishes;

    @Column(name = "create_date", updatable = false, nullable = false, columnDefinition = "DEFAULT CURRENT_DATE()")
    @NotNull
    private LocalDate createDate;
}


