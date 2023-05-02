package ru.javaops.topjava2.web.menu;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava2.model.Menu;
import ru.javaops.topjava2.repository.MenuRepository;
import ru.javaops.topjava2.to.CreateMenuTo;
import ru.javaops.topjava2.to.MenuTo;
import ru.javaops.topjava2.web.action.menu.CreateMenuAction;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.javaops.topjava2.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AdminMenuController {
    static final String REST_URL = "/api/admin/menu";

    private final CreateMenuAction createAction;
    private final MenuRepository repository;
    private final MenuMapper mapper;

    @GetMapping
    public List<MenuTo> getAll(@RequestParam @Nullable LocalDate date) {
        return mapper.toListDto(repository.findAllByDate(date));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        repository.deleteExisted(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuTo> createWithLocation(@Valid @RequestBody CreateMenuTo menu) {
        Menu created = createAction.execute(menu);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                          .path(REST_URL + "/{id}")
                                                          .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(mapper.toDto(created));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Menu menu, @PathVariable int id) {
        assureIdConsistent(menu, id);
        repository.save(menu);
    }
}
