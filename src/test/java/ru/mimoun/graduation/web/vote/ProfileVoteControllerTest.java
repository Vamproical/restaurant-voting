package ru.mimoun.graduation.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mimoun.graduation.repository.VoteRepository;
import ru.mimoun.graduation.web.AbstractControllerTest;
import ru.mimoun.graduation.web.restaurant.RestaurantTestData;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mimoun.graduation.web.user.UserTestData.USER_ID;
import static ru.mimoun.graduation.web.user.UserTestData.USER_MAIL;


class ProfileVoteControllerTest extends AbstractControllerTest {
    @Autowired
    private VoteRepository repository;

    @Value("${vote.time.constraint}")
    private LocalTime overTimeToVote;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getActualVote() throws Exception {
        perform(MockMvcRequestBuilders.get(ProfileVoteController.REST_URL + "/current"))
                .andDo(print())
                .andExpect(VoteTestData.VOTE_TO_MATCHER.contentJson(VoteTestData.voteTo));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void delete() throws Exception {
        if (LocalTime.now().isAfter(overTimeToVote)) {
            perform(MockMvcRequestBuilders.delete(ProfileVoteController.REST_URL))
                    .andDo(print())
                    .andExpect(status().isUnprocessableEntity());
        } else {
            perform(MockMvcRequestBuilders.delete(ProfileVoteController.REST_URL))
                    .andDo(print())
                    .andExpect(status().isNoContent());
            assertFalse(repository.findById(RestaurantTestData.RESTAURANT_ID).isPresent());
        }
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void vote() throws Exception {
        if (LocalTime.now().isAfter(overTimeToVote)) {
            perform(MockMvcRequestBuilders.post(ProfileVoteController.REST_URL))
                    .andDo(print())
                    .andExpect(status().isUnprocessableEntity());
        } else {
            perform(MockMvcRequestBuilders.post(ProfileVoteController.REST_URL))
                    .andDo(print())
                    .andExpect(status().isCreated());
            assertFalse(repository.findVoteByUserId(USER_ID, LocalDate.now()).isPresent());
        }
    }
}
