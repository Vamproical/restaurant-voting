package ru.javaops.topjava2.to;

import java.time.LocalDate;

public record VoteTo(RestaurantTo restaurant, UserTo user, LocalDate voteDate) {
}
