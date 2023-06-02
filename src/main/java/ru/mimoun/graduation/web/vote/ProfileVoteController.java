package ru.mimoun.graduation.web.vote;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.mimoun.graduation.service.VoteService;
import ru.mimoun.graduation.to.VoteTo;
import ru.mimoun.graduation.web.AuthUser;

@RestController
@RequestMapping(value = ProfileVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProfileVoteController {
    static final String REST_URL = "/api/profile/votes";

    private final VoteService service;
    private final VoteMapper mapper;

    @Operation(summary = "Vote for a restaurant", description = "User can vote or change his mind before 11:00")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void vote(@RequestParam("restaurantId") Integer restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        service.vote(restaurantId, authUser.id());
    }

    @Operation(summary = "Get today's user vote")
    @GetMapping("current")
    public VoteTo getCurrentVote(@AuthenticationPrincipal AuthUser authUser) {
        return mapper.toDto(service.getExistedByUser(authUser.id()));
    }
}
