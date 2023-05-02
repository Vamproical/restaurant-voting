package ru.javaops.topjava2.web.vote;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava2.service.VoteService;

import java.time.LocalTime;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class VoteController {
    static final String REST_URL = "/api/vote";
    private static final LocalTime EXPIRED_TIME = LocalTime.of(11, 0);

    private final VoteService service;
    private final VoteMapper mapper;

}
