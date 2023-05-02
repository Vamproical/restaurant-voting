package ru.javaops.topjava2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaops.topjava2.repository.VoteRepository;

@RequiredArgsConstructor
@Service
public class VoteService {
    private final VoteRepository repository;

}
