package foodordering.config;

import foodordering.category.Category;
import foodordering.category.CategoryRepository;
import foodordering.dish.Dish;
import foodordering.dish.DishImage;
import foodordering.dish.DishImageRepository;
import foodordering.dish.DishRepository;
import foodordering.user.Role;
import foodordering.user.User;
import foodordering.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final DishRepository dishRepository;
    private final DishImageRepository dishImageRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(User.builder()
            .email("admin@admin.admin")
            .firstname("admin")
            .lastname("admin")
            .password(passwordEncoder.encode("123"))
            .role(Role.ADMIN)
            .build());

        Category categorySoups = Category.builder().name("Soups").orderPos(1).createdAt(LocalDateTime.now()).build();
        Category categorySalads = Category.builder().name("Salads").orderPos(2).createdAt(LocalDateTime.now()).build();
        Category categoryMainCourses = Category.builder().name("Main courses").orderPos(3).createdAt(LocalDateTime.now()).build();
        Category categorySides = Category.builder().name("Sides").orderPos(4).createdAt(LocalDateTime.now()).build();
        Category categoryDesserts = Category.builder().name("Desserts").orderPos(5).createdAt(LocalDateTime.now()).build();

        categoryRepository.saveAll(Arrays.asList(categorySoups, categorySalads, categoryMainCourses, categorySides, categoryDesserts));

        Dish dish1 = Dish.builder()
            .name("Tomato Basil Soup")
            .description("A classic soup made with ripe tomatoes, fresh basil, and a hint of garlic. Served with a drizzle of olive oil and a sprinkle of Parmesan cheese.")
            .isEnabled(true)
            .price(1500)
            .createdAt(LocalDateTime.now())
            .category(categorySoups)
            .build();

        dishRepository.saveAll(Arrays.asList(dish1));
        dishImageRepository.saveAll(Arrays.asList(
            DishImage.builder().dish(dish1).url("https://www.ambitiouskitchen.com/wp-content/uploads/2018/08/tomatosoup-4-2-725x725.jpg").build(),
            DishImage.builder().dish(dish1).url("https://garlicsaltandlime.com/wp-content/uploads/2022/09/Creamy-tomato-basil-soup-1.jpg").build()));
    }
}
