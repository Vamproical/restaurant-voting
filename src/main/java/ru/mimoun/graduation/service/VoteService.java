package ru.mimoun.graduation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mimoun.graduation.error.NotFoundException;
import ru.mimoun.graduation.error.VoteTimeConstraintException;
import ru.mimoun.graduation.model.Vote;
import ru.mimoun.graduation.repository.RestaurantRepository;
import ru.mimoun.graduation.repository.UserRepository;
import ru.mimoun.graduation.repository.VoteRepository;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class VoteService {
    @Value("${vote.time.constraint}")
    private LocalTime overTimeToVote;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final Clock clock;

    @Transactional
    public Vote vote(int restaurantId, int userId) {
        Vote vote = new Vote(restaurantRepository.getReferenceById(restaurantId),
                             userRepository.getReferenceById(userId),
                             LocalDate.now());

        return voteRepository.save(vote);
    }

    @Transactional
    public void revote(int restaurantId, int userId) {
        LocalDate voteDate = LocalDate.now(clock);
        LocalTime voteTime = LocalTime.now(clock);
        Vote vote = voteRepository.findByUserIdAndVoteDate(userId, voteDate)
                                  .orElseThrow(() -> new NotFoundException("Vote with user_id=" + userId + " not found"));

        checkTimeToVote(voteTime);

        vote.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        voteRepository.save(vote);
    }

    private void checkTimeToVote(LocalTime time) {
        if (time.isAfter(overTimeToVote)) {
            throw new VoteTimeConstraintException("You can vote or change the vote only before 11:00 AM");
        }
    }

    public Vote getExistedByUser(int userId) {
        return voteRepository.findVoteByUserId(userId, LocalDate.now()).orElseThrow(() -> new NotFoundException("Vote with user_id=" + userId + " not found"));
    }
}
