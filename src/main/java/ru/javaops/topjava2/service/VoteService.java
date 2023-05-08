package ru.javaops.topjava2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.javaops.topjava2.error.NotFoundException;
import ru.javaops.topjava2.error.VoteTimeConstraintException;
import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.repository.UserRepository;
import ru.javaops.topjava2.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteService {
    @Value("${vote.time.constraint}")
    private LocalTime overTimeToVote;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public Vote vote(Integer restaurantId, int userId) {
        LocalDate voteDate = LocalDate.now();
        Vote vote = voteRepository.findByUserIdAndVoteDate(userId, voteDate)
                                  .orElseGet(() -> {
                                      Vote temp = new Vote();
                                      temp.setUser(userRepository.getReferenceById(userId));
                                      temp.setVoteDate(voteDate);
                                      return temp;
                                  });

        if (!vote.isNew()) {
            checkTimeToVote();
        }

        vote.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        return voteRepository.save(vote);
    }

    private void checkTimeToVote() {
        if (LocalTime.now().isAfter(overTimeToVote)) {
            throw new VoteTimeConstraintException("You can vote or change the vote only before 11:00 AM");
        }
    }

    public Vote getExistedByUser(int userId) {
        return voteRepository.findVoteByUserId(userId, LocalDate.now()).orElseThrow(() -> new NotFoundException("Vote with user_id=" + userId + " not found"));
    }

    public List<Vote> getAllForRestaurant(Integer restaurantId) {
        return voteRepository.findVotesByRestaurantId(restaurantId);
    }

    public void delete(int userId) {
        checkTimeToVote();
        Vote existedByUser = getExistedByUser(userId);
        voteRepository.delete(existedByUser);
    }
}
