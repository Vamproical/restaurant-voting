package ru.javaops.topjava2.web.vote;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava2.service.VoteService;
import ru.javaops.topjava2.to.VoteTo;

import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class VoteController {
    static final String REST_URL = "/api/vote";

    private final VoteService service;
    private final VoteMapper mapper;

    @GetMapping
    public List<VoteTo> getVotesForRestaurant(@RequestParam("restaurantId") Integer restaurantId) {
        return mapper.toDtoList(service.getAllForRestaurant(restaurantId));
    }
}
