package ru.javaops.topjava2.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava2.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava2.web.user.UserTestData.USER_MAIL;
import static ru.javaops.topjava2.web.vote.VoteController.REST_URL;
import static ru.javaops.topjava2.web.vote.VoteTestData.VOTE_LIST_TO_MATCHER;
import static ru.javaops.topjava2.web.vote.VoteTestData.voteListTo;


class VoteControllerTest extends AbstractControllerTest {
    @Test
    @WithUserDetails(value = USER_MAIL)
    void getVotesForRestaurant() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                                      .param("restaurantId", "1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_LIST_TO_MATCHER.contentJson(voteListTo));
    }
}
