package ru.mimoun.graduation.web.menu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mimoun.graduation.model.Menu;
import ru.mimoun.graduation.repository.MenuRepository;
import ru.mimoun.graduation.to.CreateMenuTo;
import ru.mimoun.graduation.to.DishTo;
import ru.mimoun.graduation.to.UpdateMenuTo;
import ru.mimoun.graduation.util.JsonUtil;
import ru.mimoun.graduation.web.AbstractControllerTest;
import ru.mimoun.graduation.web.user.UserTestData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mimoun.graduation.web.menu.MenuTestData.MENU_ID;
import static ru.mimoun.graduation.web.menu.MenuTestData.getUpdated;

class AdminMenuControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = AdminMenuController.REST_URL + '/';

    @Autowired
    private MenuRepository menuRepository;

    @Test
    @WithUserDetails(value = UserTestData.USER_MAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = UserTestData.ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + MENU_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(menuRepository.findById(MENU_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = UserTestData.ADMIN_MAIL)
    void createWithLocation() throws Exception {
        CreateMenuTo menu = new CreateMenuTo(2, List.of(new DishTo("Some Dish", 150)), null);
        ResultActions action = perform(MockMvcRequestBuilders.post(AdminMenuController.REST_URL)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(JsonUtil.writeValue(menu)))
                .andExpect(status().isCreated());

        Menu created = MenuTestData.MENU_MATCHER.readFromJson(action);
        int newId = created.id();
        MenuTestData.MENU_MATCHER.assertMatch(menuRepository.getExisted(newId), created);
    }

    @Test
    @WithUserDetails(value = UserTestData.ADMIN_MAIL)
    void update() throws Exception {
        UpdateMenuTo menu = new UpdateMenuTo(2, List.of(new DishTo("Updated Dish", 100)));
        ResultActions action = perform(MockMvcRequestBuilders.put(REST_URL_SLASH + MENU_ID)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(JsonUtil.writeValue(menu)))
                .andExpect(status().isNoContent());

        Menu updated = getUpdated();
        MenuTestData.MENU_MATCHER.assertMatch(menuRepository.getExisted(MENU_ID), updated);
    }

    @Test
    @WithUserDetails(value = UserTestData.ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(AdminMenuController.REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MenuTestData.MENU_TO_MATCHER.contentJson(MenuTestData.menuTo2, MenuTestData.menuTo));
    }

    @Test
    @WithUserDetails(value = UserTestData.ADMIN_MAIL)
    void getAllByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "by-date")
                                      .param("date", "2023-05-01"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MenuTestData.MENU_TO_MATCHER.contentJson(List.of(MenuTestData.menuTo2)));
    }
}
