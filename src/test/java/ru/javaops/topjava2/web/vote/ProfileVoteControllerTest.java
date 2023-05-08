package ru.javaops.topjava2.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava2.repository.VoteRepository;
import ru.javaops.topjava2.web.AbstractControllerTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.RESTAURANT_ID;
import static ru.javaops.topjava2.web.user.UserTestData.USER_ID;
import static ru.javaops.topjava2.web.user.UserTestData.USER_MAIL;
import static ru.javaops.topjava2.web.vote.ProfileVoteController.REST_URL;
import static ru.javaops.topjava2.web.vote.VoteTestData.VOTE_TO_MATCHER;
import static ru.javaops.topjava2.web.vote.VoteTestData.voteTo;


class ProfileVoteControllerTest extends AbstractControllerTest {
    @Autowired
    private VoteRepository repository;

    @Value("${vote.time.constraint}")
    private LocalTime overTimeToVote;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getActualVote() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andDo(print())
                .andExpect(VOTE_TO_MATCHER.contentJson(voteTo));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void delete() throws Exception {
        if (LocalTime.now().isAfter(overTimeToVote)) {
            perform(MockMvcRequestBuilders.delete(REST_URL))
                    .andDo(print())
                    .andExpect(status().isUnprocessableEntity());
        } else {
            perform(MockMvcRequestBuilders.delete(REST_URL))
                    .andDo(print())
                    .andExpect(status().isNoContent());
            assertFalse(repository.findById(RESTAURANT_ID).isPresent());
        }
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void vote() throws Exception {
        if (LocalTime.now().isAfter(overTimeToVote)) {
            perform(MockMvcRequestBuilders.post(REST_URL))
                    .andDo(print())
                    .andExpect(status().isUnprocessableEntity());
        } else {
            perform(MockMvcRequestBuilders.post(REST_URL))
                    .andDo(print())
                    .andExpect(status().isCreated());
            assertFalse(repository.findVoteByUserId(USER_ID, LocalDate.now()).isPresent());
        }
    }
}
