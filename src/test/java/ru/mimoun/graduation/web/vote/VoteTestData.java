package ru.mimoun.graduation.web.vote;

import ru.mimoun.graduation.to.VoteTo;
import ru.mimoun.graduation.web.MatcherFactory;
import ru.mimoun.graduation.web.restaurant.RestaurantTestData;

import java.time.LocalDate;

public class VoteTestData {
    public static final MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingEqualsComparator(VoteTo.class);
    public static final VoteTo voteTo = new VoteTo(RestaurantTestData.restaurantTo, LocalDate.now());

}
