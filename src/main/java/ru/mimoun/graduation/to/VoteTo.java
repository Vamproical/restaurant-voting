package ru.mimoun.graduation.to;

import java.time.LocalDate;

public record VoteTo(RestaurantTo restaurant, LocalDate voteDate) {
}
