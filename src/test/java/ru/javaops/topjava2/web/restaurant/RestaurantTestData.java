package ru.javaops.topjava2.web.restaurant;

import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.to.RestaurantTo;
import ru.javaops.topjava2.web.MatcherFactory;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingEqualsComparator(Restaurant.class);
    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);
    public static final int RESTAURANT_ID = 1;
    public static final Restaurant restaurant = new Restaurant(1, "Sample Restaurant");
    public static final RestaurantTo restaurantTo = new RestaurantTo(1, "Sample Restaurant");
    public static final Restaurant restaurant2 = new Restaurant(2, "Invisible");
    public static final RestaurantTo restaurantTo2 = new RestaurantTo(2, "Invisible");
}
