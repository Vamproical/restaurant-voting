package ru.javaops.topjava2.web.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.repository.MenuRepository;
import ru.javaops.topjava2.to.MenuTo;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserMenuController {
    static final String REST_URL = "/api/menus";

    private final MenuRepository repository;
    private final MenuMapper mapper;

    @GetMapping("/{id}")
    public MenuTo get(@PathVariable int id) {
        return mapper.toDto(repository.getExisted(id));
    }

    @GetMapping
    public List<MenuTo> getAllForToday() {
        return mapper.toListDto(repository.findAllByDate(LocalDate.now()));
    }

    @GetMapping("by-restaurant")
    public List<MenuTo> getAllByRestaurant(@RequestParam Integer restaurantId) {
        return mapper.toListDto(repository.findAllByRestaurant(restaurantId));
    }
}
