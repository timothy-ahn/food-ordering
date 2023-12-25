package foodordering.dish;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/dish")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @PostMapping("admin")
    public ResponseEntity<Integer> updateDish(@RequestBody CreateDishRequest request) {
        return ResponseEntity.ok(dishService.create(request).getId());
    }

    @PutMapping("admin")
    public ResponseEntity<Integer> updateDish(@RequestBody @NotNull UpdateDishRequest request) {
        Optional<Dish> dish = dishService.getById(request.getId());
        return dish
            .map(value -> ResponseEntity.ok(dishService.update(value, request).getId()))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{dishId}")
    public ResponseEntity<DishDto> getDetails(@PathVariable("dishId") Integer dishId) {
        Optional<Dish> dish = dishService.getById(dishId);
        return dish
            .map(value -> ResponseEntity.ok(DishDto.mapFromDish(value)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<DishDto>> getAll() {
        return ResponseEntity
            .ok(dishService.getAll().stream().map(DishDto::mapFromDish).toList());
    }

    @DeleteMapping("admin/{dishId}")
    public ResponseEntity<Object> deleteDish(@PathVariable("dishId") Integer dishId) {
        dishService.delete(dishId);
        return ResponseEntity.noContent().build();
    }
}
