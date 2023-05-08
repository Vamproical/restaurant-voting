package ru.mimoun.graduation.web.vote;

import org.mapstruct.Mapper;
import ru.mimoun.graduation.model.Vote;
import ru.mimoun.graduation.to.VoteTo;

@Mapper
public interface VoteMapper {
    VoteTo toDto(Vote vote);
}
