package ru.javaops.topjava2.to;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo {
    public RestaurantTo(Integer id, String name) {
        super(id, name);
    }
}
