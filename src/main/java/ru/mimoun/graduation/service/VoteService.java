package ru.mimoun.graduation.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mimoun.graduation.error.NotFoundException;
import ru.mimoun.graduation.error.VoteTimeConstraintException;
import ru.mimoun.graduation.model.Vote;
import ru.mimoun.graduation.repository.RestaurantRepository;
import ru.mimoun.graduation.repository.UserRepository;
import ru.mimoun.graduation.repository.VoteRepository;

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

    public Vote vote(@NonNull Integer restaurantId, @NonNull Integer userId) {
        Vote vote = new Vote(restaurantRepository.getReferenceById(restaurantId),
                             userRepository.getReferenceById(userId),
                             LocalDate.now());

        return voteRepository.save(vote);
    }

    public Vote revote(@NonNull Integer restaurantId, @NonNull Integer userId) {
        LocalDate voteDate = LocalDate.now();
        Vote vote = voteRepository.findByUserIdAndVoteDate(userId, voteDate)
                                  .orElseThrow(() -> new NotFoundException("Vote with user_id=" + userId + " not found"));

        checkTimeToVote();
        vote.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        return voteRepository.save(vote);
    }

    private void checkTimeToVote() {
        if (LocalTime.now().isAfter(overTimeToVote)) {
            throw new VoteTimeConstraintException("You can vote or change the vote only before 11:00 AM");
        }
    }

    public Vote getExistedByUser(@NonNull Integer userId) {
        return voteRepository.findVoteByUserId(userId, LocalDate.now()).orElseThrow(() -> new NotFoundException("Vote with user_id=" + userId + " not found"));
    }
}
