package ru.javaops.topjava2.web.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava2.repository.MenuRepository;
import ru.javaops.topjava2.to.MenuTo;

import java.util.List;

@RestController
@RequestMapping(value = UserMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserMenuController {
    static final String REST_URL = "/api/menu";

    private final MenuRepository repository;
    private final MenuMapper mapper;

    @GetMapping("/{id}")
    public MenuTo get(@PathVariable int id) {
        return mapper.toDto(repository.getExisted(id));
    }

    @GetMapping
    public List<MenuTo> getAll() {
        return mapper.toListDto(repository.findAll());
    }
}
