package ru.javaops.topjava2.web.dish;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava2.repository.DishRepository;
import ru.javaops.topjava2.to.DishTo;

import java.util.List;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserDishController {
    static final String REST_URL = "/api/dish";

    private final DishRepository repository;
    private final DishMapper mapper;

    @GetMapping("/{id}")
    public DishTo get(@PathVariable int id) {
        return mapper.toDto(repository.getExisted(id));
    }

    @GetMapping
    public List<DishTo> getAll() {
        return mapper.toListDto(repository.findAll(Sort.by(Sort.Direction.ASC, "name")));
    }
}
