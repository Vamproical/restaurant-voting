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

import java.time.LocalDate;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class VoteController {
    static final String REST_URL = "/api/votes";

    private final VoteService service;

    @Operation(summary = "Get count of votes for specified restaurant and specified date",
               description = "If the date is not passed, it will be returned for today")
    @GetMapping
    public VoteListTo getVotesForRestaurant(@RequestParam("restaurantId") Integer restaurantId,
                                            @RequestParam(required = false) LocalDate date) {
        return new VoteListTo(restaurantId, service.getAllForRestaurant(restaurantId,
                                                                        date == null ? LocalDate.now() : date).size());
    }
}
