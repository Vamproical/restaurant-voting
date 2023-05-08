package ru.mimoun.graduation.web.vote;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mimoun.graduation.service.VoteService;
import ru.mimoun.graduation.to.VoteListTo;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class VoteController {
    static final String REST_URL = "/api/votes";

    private final VoteService service;

    @Operation(summary = "Get count of votes for specified restaurant")
    @GetMapping
    public VoteListTo getVotesForRestaurant(@RequestParam("restaurantId") Integer restaurantId) {
        return new VoteListTo(restaurantId, service.getAllForRestaurant(restaurantId).size());
    }
}
