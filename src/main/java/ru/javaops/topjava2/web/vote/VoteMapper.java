package ru.javaops.topjava2.web.vote;

import org.mapstruct.Mapper;
import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.to.VoteTo;

import java.util.List;

@Mapper
public interface VoteMapper {
    VoteTo toDto(Vote vote);

    List<VoteTo> toDtoList(List<Vote> votes);
}
