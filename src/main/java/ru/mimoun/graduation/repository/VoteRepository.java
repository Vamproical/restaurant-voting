package ru.mimoun.graduation.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.mimoun.graduation.model.Vote;

import java.time.LocalDate;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {
    @EntityGraph(attributePaths = {"user", "restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.voteDate=:voteDate")
    Optional<Vote> findByUserIdAndVoteDate(@Param("userId") int userId, @NotNull @Param("voteDate") LocalDate voteDate);

    @EntityGraph(attributePaths = {"user", "restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.voteDate=:voteDate")
    Optional<Vote> findVoteByUserId(@Param("userId") int userId, @NotNull @Param("voteDate") LocalDate voteDate);
}
