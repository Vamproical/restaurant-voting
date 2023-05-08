package ru.mimoun.graduation.web.vote;

import ru.mimoun.graduation.to.VoteListTo;
import ru.mimoun.graduation.to.VoteTo;
import ru.mimoun.graduation.web.MatcherFactory;
import ru.mimoun.graduation.web.restaurant.RestaurantTestData;
import ru.mimoun.graduation.web.user.UserTestData;

import java.time.LocalDate;

public class VoteTestData {
    public static final MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(VoteTo.class, "user.password");
    public static final MatcherFactory.Matcher<VoteListTo> VOTE_LIST_TO_MATCHER = MatcherFactory.usingEqualsComparator(VoteListTo.class);
    public static final VoteListTo voteListTo = new VoteListTo(1, 1);
    public static final VoteTo voteTo = new VoteTo(RestaurantTestData.restaurantTo, UserTestData.userTo, LocalDate.now());

}
