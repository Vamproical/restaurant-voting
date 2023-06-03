package ru.mimoun.graduation.web.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mimoun.graduation.repository.RestaurantRepository;
import ru.mimoun.graduation.to.RestaurantTo;

import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CacheConfig(cacheNames = "restaurants")
public class UserRestaurantController {
    static final String REST_URL = "/api/restaurants";

    private final RestaurantRepository repository;
    private final RestaurantMapper mapper;

    @Operation(summary = "Get restaurant by id")
    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable int id) {
        return mapper.toDto(repository.getExisted(id));
    }

    @Operation(summary = "Get list of restaurant")
    @GetMapping
    @Cacheable
    public List<RestaurantTo> getAll() {
        return mapper.toListDto(repository.findAll());
    }

    @Operation(summary = "Get list of restaurant by name",
               parameters = @Parameter(name = "name", description = "Restaurant name ignore case"))
    @GetMapping("/by-name")
    public List<RestaurantTo> getAllByName(@RequestParam String name) {
        return mapper.toListDto(repository.findAllByNameContainsIgnoreCase(name));
    }
}
