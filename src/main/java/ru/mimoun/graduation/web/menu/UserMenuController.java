package ru.mimoun.graduation.web.menu;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mimoun.graduation.repository.MenuRepository;
import ru.mimoun.graduation.to.MenuTo;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserMenuController {
    static final String REST_URL = "/api/menus";

    private final MenuRepository repository;
    private final MenuMapper mapper;

    @Operation(summary = "Get menu by id")
    @GetMapping("/{id}")
    public MenuTo get(@PathVariable int id) {
        return mapper.toDto(repository.getExisted(id));
    }

    @Operation(summary = "Get list of menu for today")
    @GetMapping
    public List<MenuTo> getAllForToday() {
        return mapper.toListDto(repository.findAllByDate(LocalDate.now()));
    }

    @Operation(summary = "Get list of menu ")
    @GetMapping("by-restaurant")
    public List<MenuTo> getAllByRestaurant(@RequestParam Integer restaurantId) {
        return mapper.toListDto(repository.findAllByRestaurant(restaurantId));
    }
}
