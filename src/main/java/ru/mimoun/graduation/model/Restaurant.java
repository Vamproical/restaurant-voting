package ru.mimoun.graduation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "restaurant", uniqueConstraints = {
        @UniqueConstraint(name = "unique_name_restaurant", columnNames = "name")})
@Getter
@Setter
@NoArgsConstructor
public class Restaurant extends NamedEntity implements Serializable {
    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
