package ru.mimoun.graduation.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mimoun.graduation.model.Restaurant;
import ru.mimoun.graduation.repository.RestaurantRepository;
import ru.mimoun.graduation.to.RestaurantTo;
import ru.mimoun.graduation.util.JsonUtil;
import ru.mimoun.graduation.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mimoun.graduation.web.restaurant.AdminRestaurantController.REST_URL;
import static ru.mimoun.graduation.web.restaurant.RestaurantTestData.RESTAURANT_ID;
import static ru.mimoun.graduation.web.restaurant.RestaurantTestData.RESTAURANT_MATCHER;
import static ru.mimoun.graduation.web.user.UserTestData.ADMIN_MAIL;
import static ru.mimoun.graduation.web.user.UserTestData.USER_MAIL;

class AdminRestaurantControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + RESTAURANT_ID))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + RESTAURANT_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(restaurantRepository.findById(RESTAURANT_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "New Restaurant");
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(JsonUtil.writeValue(newRestaurant)))
                .andExpect(status().isCreated());

        Restaurant created = RESTAURANT_MATCHER.readFromJson(action);
        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.getExisted(newId), newRestaurant);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        RestaurantTo updatedTo = new RestaurantTo(RESTAURANT_ID, "Updated Restaurant");
        Restaurant expected = new Restaurant(RESTAURANT_ID, "Updated Restaurant");

        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + RESTAURANT_ID).contentType(MediaType.APPLICATION_JSON)
                                      .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isNoContent());

        RESTAURANT_MATCHER.assertMatch(restaurantRepository.getExisted(RESTAURANT_ID), expected);
    }
}
