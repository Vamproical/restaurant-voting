package ru.mimoun.graduation.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.mimoun.graduation.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {
    @Query("SELECT v from Vote v where v.user.id=:userId and v.voteDate=:voteDate")
    Optional<Vote> findByUserIdAndVoteDate(@Param("userId") Integer userId, @Param("voteDate") LocalDate voteDate);

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId")
    List<Vote> findVotesByRestaurantId(@Param("restaurantId") Integer restaurantId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId and v.voteDate=:voteDate")
    Optional<Vote> findVoteByUserId(@Param("userId") Integer userId, @Param("voteDate") LocalDate voteDate);
}
