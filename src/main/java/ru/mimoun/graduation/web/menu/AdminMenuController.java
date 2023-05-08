package ru.mimoun.graduation.web.menu;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mimoun.graduation.model.Menu;
import ru.mimoun.graduation.repository.MenuRepository;
import ru.mimoun.graduation.to.CreateMenuTo;
import ru.mimoun.graduation.to.MenuTo;
import ru.mimoun.graduation.to.UpdateMenuTo;
import ru.mimoun.graduation.web.action.menu.CreateMenuAction;
import ru.mimoun.graduation.web.action.menu.UpdateMenuAction;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AdminMenuController {
    static final String REST_URL = "/api/admin/menus";

    private final CreateMenuAction createAction;
    private final UpdateMenuAction updateAction;
    private final MenuRepository repository;
    private final MenuMapper mapper;

    @Operation(summary = "Get a list of menu by specified date")
    @GetMapping("/by-date")
    public List<MenuTo> getAllByDate(@RequestParam LocalDate date) {
        return mapper.toListDto(repository.findAllByDate(date));
    }

    @Operation(summary = "Get a list of menu",
               parameters = @Parameter(name = "sort", description = "Specified fields and direction to sort the list"))
    @GetMapping
    public List<MenuTo> getAll(@SortDefault(value = "date") Sort sort) {
        return mapper.toListDto(repository.findAll(sort));
    }

    @Operation(summary = "Delete menu by id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        repository.deleteExisted(id);
    }

    @Operation(summary = "Create menu")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MenuTo> createWithLocation(@Valid @RequestBody CreateMenuTo menu) {
        Menu created = createAction.execute(menu);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                          .path(REST_URL + "/{id}")
                                                          .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(mapper.toDto(created));
    }

    @Operation(summary = "Update menu by id", description = "Administrator can update only list of dish and restaurant on menu")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UpdateMenuTo menu, @PathVariable int id) {
        updateAction.execute(menu, id);
    }
}
