package ru.javaops.topjava2.web.restaurant;

import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.web.MatcherFactory;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingEqualsComparator(Restaurant.class);
    protected static final int RESTAURANT_ID = 10;
    public static final Restaurant restaurant = new Restaurant(10, "Simple Restaurant");
    public static final Restaurant restaurant2 = new Restaurant(11, "Invisible");
}
