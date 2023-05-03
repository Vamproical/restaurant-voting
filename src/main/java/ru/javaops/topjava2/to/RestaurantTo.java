package ru.javaops.topjava2.to;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.javaops.topjava2.HasId;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo implements HasId {
    public RestaurantTo(Integer id, String name) {
        super(id, name);
    }
}
