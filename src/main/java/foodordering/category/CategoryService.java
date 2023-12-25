package foodordering.category;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> getById(Integer id) {
        return categoryRepository.findById(id);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category create(Category category) {
        category.setCreatedAt(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    public Category update(@NotNull Category category) {
        Category oldCategory = categoryRepository
                .findById(category.getId()).orElseThrow();
        oldCategory.setName(category.getName());
        oldCategory.setOrderPos(category.getOrderPos());
        oldCategory.setUpdatedAt(LocalDateTime.now());

        return categoryRepository.save(oldCategory);
    }

    public void delete(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
