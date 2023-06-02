package ru.mimoun.graduation.web.vote;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mimoun.graduation.model.Vote;
import ru.mimoun.graduation.service.VoteService;
import ru.mimoun.graduation.to.VoteTo;
import ru.mimoun.graduation.web.AuthUser;

import java.net.URI;

@RestController
@RequestMapping(value = ProfileVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProfileVoteController {
    static final String REST_URL = "/api/profile/votes";

    private final VoteService service;
    private final VoteMapper mapper;

    @Operation(summary = "Vote for a restaurant", description = "User can vote before 11:00")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VoteTo> vote(@RequestParam("restaurantId") Integer restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        Vote vote = service.vote(restaurantId, authUser.id());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                          .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(mapper.toDto(vote));
    }

    @Operation(summary = "Revote for a restaurant", description = "User can change his mind before 11:00")
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void revote(@RequestParam("restaurantId") Integer restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        service.revote(restaurantId, authUser.id());
    }


    @Operation(summary = "Get today's user vote")
    @GetMapping("current")
    public VoteTo getCurrentVote(@AuthenticationPrincipal AuthUser authUser) {
        return mapper.toDto(service.getExistedByUser(authUser.id()));
    }
}
