package foodordering.menu;

import foodordering.category.Category;
import foodordering.category.CategoryRepository;
import foodordering.dish.Dish;
import foodordering.dish.DishImage;
import foodordering.dish.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final DishRepository dishRepository;
    private final CategoryRepository categoryRepository;

    public MenuDto getMenu() {
        MenuDto menu = new MenuDto();
        List<Category> categories = categoryRepository.findAll();

        for (Category category : categories) {
          List<Dish> dishes = dishRepository.findDishesByCategory_Id(category.getId());
          menu.getCategories()
              .add(MenuCategoryDto.builder()
                  .id(category.getId())
                  .name(category.getName())
                  .dishes(dishes.stream().map(dish -> MenuDishDto.builder()
                      .name(dish.getName())
                      .description(dish.getDescription())
                      .price(dish.getPrice())
                      .images(dish.getDishImages().stream().map(DishImage::getUrl)
                              .toList()).build()).toList()).build());
        }

        return menu;
    }
}
