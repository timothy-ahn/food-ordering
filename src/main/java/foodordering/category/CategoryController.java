package foodordering.category;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(
            categoryService.getCategories()
                .stream()
                .map(CategoryDto::mapFromCategory)
                .toList()
        );
    }

    @PostMapping("admin")
    public ResponseEntity<Integer> createCategory(@RequestBody @NotNull CreateCategoryRequest request) {
        return ResponseEntity.ok(categoryService.create(
            Category.builder().name(request.getName()).isEnabled(request.getIsEnabled()).orderPos(request.getOrder()).build()).getId());
    }

    @PutMapping("admin")
    public ResponseEntity<Integer> updateCategory(@RequestBody @NotNull UpdateCategoryRequest request) {
        Optional<Category> category = categoryService.getById(request.getId());
        if (category.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(categoryService.update(
                Category.builder().id(request.getId()).name(request.getName())
                    .isEnabled(request.getIsEnabled()).orderPos(request.getOrder()).build()).getId());
    }

    @DeleteMapping("admin/{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }
}
