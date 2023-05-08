package ru.mimoun.graduation.web.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mimoun.graduation.repository.RestaurantRepository;
import ru.mimoun.graduation.to.RestaurantTo;

import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserRestaurantController {
    static final String REST_URL = "/api/restaurants";

    private final RestaurantRepository repository;
    private final RestaurantMapper mapper;

    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable int id) {
        return mapper.toDto(repository.getExisted(id));
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        return mapper.toListDto(repository.findAll());
    }

    @GetMapping("/by-name")
    public List<RestaurantTo> getAllByName(@RequestParam String name) {
        return mapper.toListDto(repository.findAllByNameContainsIgnoreCase(name));
    }
}
