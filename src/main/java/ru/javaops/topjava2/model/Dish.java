package ru.javaops.topjava2.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    @Column(name = "dish_name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "dish_price", nullable = false)
    @NotNull
    private Integer price;
}
