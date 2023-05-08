package ru.javaops.topjava2.web.vote;

import ru.javaops.topjava2.to.VoteListTo;
import ru.javaops.topjava2.to.VoteTo;
import ru.javaops.topjava2.web.MatcherFactory;
import ru.javaops.topjava2.web.user.UserTestData;

import java.time.LocalDate;

import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.restaurantTo;

public class VoteTestData {
    public static final MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(VoteTo.class, "user.password");
    public static final MatcherFactory.Matcher<VoteListTo> VOTE_LIST_TO_MATCHER = MatcherFactory.usingEqualsComparator(VoteListTo.class);
    public static final VoteListTo voteListTo = new VoteListTo(1, 1);
    public static final VoteTo voteTo = new VoteTo(restaurantTo, UserTestData.userTo, LocalDate.now());

}
