package ru.javaops.topjava2.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class DishTo extends NamedTo {
    Integer price;

    public DishTo(Integer id, String name, Integer price) {
        super(id, name);
        this.price = price;
    }

    @Override
    public String toString() {
        return "DishTo{" +
               "name=" + name +
               "price=" + price +
               '}';
    }
}
