package ru.javaops.topjava2.web.vote;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.service.VoteService;
import ru.javaops.topjava2.to.VoteTo;
import ru.javaops.topjava2.web.AuthUser;

@RestController
@RequestMapping(value = ProfileVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProfileVoteController {
    static final String REST_URL = "/api/profile/votes";

    private final VoteService service;
    private final VoteMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void vote(@RequestParam("restaurantId") Integer restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        service.vote(restaurantId, authUser.id());
    }

    @GetMapping
    public VoteTo getCurrentVote(@AuthenticationPrincipal AuthUser authUser) {
        return mapper.toDto(service.getExistedByUser(authUser.id()));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser) {
        service.delete(authUser.id());
    }
}
