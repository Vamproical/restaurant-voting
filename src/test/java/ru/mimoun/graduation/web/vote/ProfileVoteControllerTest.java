package ru.mimoun.graduation.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mimoun.graduation.web.AbstractControllerTest;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mimoun.graduation.web.user.UserTestData.ADMIN_MAIL;
import static ru.mimoun.graduation.web.user.UserTestData.USER_MAIL;

@TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true")
class ProfileVoteControllerTest extends AbstractControllerTest {
    @Test
    @WithUserDetails(value = USER_MAIL)
    void getActualVote() throws Exception {
        perform(MockMvcRequestBuilders.get(ProfileVoteController.REST_URL + "/current"))
                .andDo(print())
                .andExpect(VoteTestData.VOTE_TO_MATCHER.contentJson(VoteTestData.voteTo));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void vote() throws Exception {
        perform(MockMvcRequestBuilders.post(ProfileVoteController.REST_URL)
                                      .param("restaurantId", Integer.toString(1)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void voteWithException() throws Exception {
        perform(MockMvcRequestBuilders.post(ProfileVoteController.REST_URL)
                                      .param("restaurantId", Integer.toString(1)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void revote() throws Exception {
        perform(MockMvcRequestBuilders.put(ProfileVoteController.REST_URL)
                                      .param("restaurantId", Integer.toString(1)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @TestConfiguration
    public static class TestConfig {
        @Bean
        public Clock clock() {
            return Clock.fixed(Instant.parse(LocalDate.now() + "T10:00:00.00Z"), ZoneId.of("Z"));
        }
    }
}
