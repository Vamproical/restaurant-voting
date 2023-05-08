package ru.mimoun.graduation.web.menu;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mimoun.graduation.web.AbstractControllerTest;
import ru.mimoun.graduation.web.user.UserTestData;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserMenuControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = UserMenuController.REST_URL + '/';

    @Test
    @WithUserDetails(value = UserTestData.USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MenuTestData.MENU_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MenuTestData.MENU_TO_MATCHER.contentJson(MenuTestData.menuTo));
    }

    @Test
    @WithUserDetails(value = UserTestData.USER_MAIL)
    void getAllForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(UserMenuController.REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MenuTestData.MENU_TO_MATCHER.contentJson(List.of(MenuTestData.menuTo)));
    }

    @Test
    @WithUserDetails(value = UserTestData.USER_MAIL)
    void getAllByRestaurant() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "by-restaurant")
                                      .param("restaurantId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MenuTestData.MENU_TO_MATCHER.contentJson(List.of(MenuTestData.menuTo)));
    }
}
