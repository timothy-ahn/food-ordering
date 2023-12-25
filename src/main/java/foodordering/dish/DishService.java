package foodordering.dish;

import foodordering.category.Category;
import foodordering.category.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final DishImageRepository dishImageRepository;
    private final CategoryRepository categoryRepository;

    public Dish create(@NotNull CreateDishRequest request) {
        Category category = categoryRepository.findById((request.getCategoryId())).orElseThrow();
        Dish newDish = Dish.builder()
            .name(request.getName())
            .description(request.getDescription())
            .category(category)
            .createdAt(LocalDateTime.now())
            .isEnabled(true)
            .build();

        dishRepository.save(newDish);

        if (!request.getImages().isEmpty()) {
            List<DishImage> images = request.getImages()
                .stream().map(img -> DishImage.builder().dish(newDish).url(img).build()).toList();

            dishImageRepository.saveAll(images);
        }

        return newDish;
    }

    @Transactional
    public Dish update(Dish dish, @NotNull UpdateDishRequest request) {
        if (request.getName() != null && !request.getName().isEmpty())
            dish.setName(request.getName());
        if (request.getDescription() != null && !request.getDescription().isEmpty())
            dish.setDescription(request.getDescription());
        if (request.getCategoryId() != null)
        {
            Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow();
            dish.setCategory(category);
        }
        dishRepository.save(dish);

        if (request.getImages() != null) {
            dishImageRepository.deleteAllByDish_Id(dish.getId());
            dishImageRepository.saveAll(request.getImages().stream().map(img -> DishImage.builder().url(img).dish(dish).build()).toList());
        }
        return dish;
    }

    public Optional<Dish> getById(Integer dishId) {
        return dishRepository.findById(dishId);
    }

    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    public void delete(Integer id) {
        dishRepository.deleteById(id);
    }
}
