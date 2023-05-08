package ru.javaops.topjava2.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteTo extends BaseTo {
    RestaurantTo restaurant;
    UserTo user;
    LocalDate voteDate;
}
