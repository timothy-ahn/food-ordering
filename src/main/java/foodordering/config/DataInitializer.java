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

        Dish soup1 = Dish.builder()
            .name("Tomato Basil Soup")
            .description("A classic soup made with ripe tomatoes, fresh basil, and a hint of garlic. Served with a drizzle of olive oil and a sprinkle of Parmesan cheese.")
            .isEnabled(true)
            .price(2500)
            .createdAt(LocalDateTime.now())
            .category(categorySoups)
            .build();

        Dish soup2 = Dish.builder()
            .name("Spicy Thai Coconut Soup (Tom Kha Gai)")
            .description("Transport your taste buds to Thailand with this aromatic soup featuring tender chicken, mushrooms, lemongrass, and galangal in a spicy coconut broth. Topped with cilantro and a squeeze of lime for an extra kick.")
            .isEnabled(true)
            .price(2800)
            .createdAt(LocalDateTime.now())
            .category(categorySoups)
            .build();

        Dish salad1 = Dish.builder()
            .name("Mediterranean Quinoa Salad")
            .description("A refreshing combination of fluffy quinoa, cherry tomatoes, cucumber, Kalamata olives, and feta cheese, tossed in a zesty lemon vinaigrette. This vibrant salad is a burst of Mediterranean flavors.")
            .isEnabled(true)
            .price(1900)
            .createdAt(LocalDateTime.now())
            .category(categorySalads)
            .build();

        Dish salad2 = Dish.builder()
            .name("Grilled Chicken Caesar Salad")
            .description("Grilled chicken breast, crisp romaine lettuce, cherry tomatoes, and homemade croutons come together in this classic Caesar salad. Topped with shaved Parmesan and drizzled with a creamy Caesar dressing for a satisfying meal.")
            .isEnabled(true)
            .price(2000)
            .createdAt(LocalDateTime.now())
            .category(categorySalads)
            .build();

        Dish mainCourse1 = Dish.builder()
            .name("Beef Tenderloin with Red Wine Reduction")
            .description("Succulent beef tenderloin cooked to perfection, accompanied by a luscious red wine reduction sauce. Served with garlic mashed potatoes and sautéed seasonal vegetables, this dish is a symphony of flavors and textures.")
            .isEnabled(true)
            .price(3800)
            .createdAt(LocalDateTime.now())
            .category(categoryMainCourses)
            .build();

        Dish mainCourse2 = Dish.builder()
            .name("Lemon Herb Grilled Salmon")
            .description("A light and flavorful dish featuring grilled salmon fillets marinated in a blend of fresh lemon, herbs, and olive oil. Served with quinoa pilaf and roasted asparagus, this dish is a healthy and delicious choice for seafood lovers.")
            .isEnabled(true)
            .price(3800)
            .createdAt(LocalDateTime.now())
            .category(categoryMainCourses)
            .build();

        Dish sides1 = Dish.builder()
            .name("Truffle Parmesan Fries")
            .description("Crispy golden fries tossed in truffle oil and dusted with grated Parmesan cheese. Served with a side of creamy truffle aioli, these fries elevate the classic side dish to a gourmet delight.")
            .isEnabled(true)
            .price(1500)
            .createdAt(LocalDateTime.now())
            .category(categorySides)
            .build();

        Dish sides2 = Dish.builder()
            .name("Garlic Butter Sauteed Spinach")
            .description("Fresh spinach leaves sautéed to perfection in a savory garlic butter sauce. This side dish is a delightful combination of tender spinach, aromatic garlic, and rich butter, creating a flavorful complement to any main course.")
            .isEnabled(true)
            .price(1600)
            .createdAt(LocalDateTime.now())
            .category(categorySides)
            .build();

        Dish dessert1 = Dish.builder()
            .name("Molten Chocolate Lava Cake")
            .description("Indulge your sweet tooth with a decadent molten chocolate lava cake. A rich and gooey chocolate center is encased in a moist chocolate cake shell, served warm and topped with a dusting of powdered sugar and a scoop of vanilla ice cream.")
            .isEnabled(true)
            .price(2000)
            .createdAt(LocalDateTime.now())
            .category(categoryDesserts)
            .build();

        Dish dessert2 = Dish.builder()
            .name("Berry Panna Cotta")
            .description("A silky and creamy panna cotta infused with the natural sweetness of mixed berries. Topped with a vibrant berry compote and garnished with fresh mint, this dessert is a delightful combination of smooth textures and fruity flavors.")
            .isEnabled(true)
            .price(2400)
            .createdAt(LocalDateTime.now())
            .category(categoryDesserts)
            .build();

        dishRepository.saveAll(Arrays.asList(
            soup1, soup2,
            salad1, salad2,
            mainCourse1, mainCourse2,
            sides1, sides2,
            dessert1, dessert2));

        dishImageRepository.saveAll(Arrays.asList(
            DishImage.builder().dish(soup1).url("https://www.ambitiouskitchen.com/wp-content/uploads/2018/08/tomatosoup-4-2-725x725.jpg").build(),
            DishImage.builder().dish(soup1).url("https://garlicsaltandlime.com/wp-content/uploads/2022/09/Creamy-tomato-basil-soup-1.jpg").build()));

        dishImageRepository.saveAll(Arrays.asList(
            DishImage.builder().dish(soup2).url("https://www.kitchensanctuary.com/wp-content/uploads/2022/04/Tom-Kha-Gai-square-FS-9.jpg").build(),
            DishImage.builder().dish(soup2).url("https://www.sugarandsoul.co/wp-content/uploads/2019/03/tom-kha-gai-chicken-coconut-soup-recipe-10.jpg").build()));

        dishImageRepository.saveAll(Arrays.asList(
            DishImage.builder().dish(salad1).url("https://cdn.apartmenttherapy.info/image/upload/f_auto,q_auto:eco,c_fill,g_center,w_730,h_913/k%2FPhoto%2FRecipes%2F2019-11-recipe-mediterranean-quinoa-salad%2F2019-10-21_Kitchn89095_Mediteranean-Quinoa-Salad").build(),
            DishImage.builder().dish(salad1).url("https://cdn.loveandlemons.com/wp-content/uploads/2020/08/quinoa-salad-recipes.jpg").build()));

        dishImageRepository.saveAll(Arrays.asList(
            DishImage.builder().dish(salad2).url("https://heartbeetkitchen.com/foodblog/wp-content/uploads/2022/06/ultimate-grilled-chicken-caesar-salad.jpg").build(),
            DishImage.builder().dish(salad2).url("https://s23209.pcdn.co/wp-content/uploads/2023/01/220905_DD_Chx-Caesar-Salad_051.jpg").build()));

        dishImageRepository.saveAll(Arrays.asList(
            DishImage.builder().dish(mainCourse1).url("https://howtofeedaloon.com/wp-content/uploads/2021/12/roast-on-a-platter.jpg").build(),
            DishImage.builder().dish(mainCourse1).url("https://howtofeedaloon.com/wp-content/uploads/2021/12/overhead-of-meat-on-a-platter.jpg").build()));

        dishImageRepository.saveAll(Arrays.asList(
            DishImage.builder().dish(mainCourse2).url("https://www.cookingclassy.com/wp-content/uploads/2018/05/grilled-lemon-herb-salmon-7.jpg").build(),
            DishImage.builder().dish(mainCourse2).url("https://www.cookingclassy.com/wp-content/uploads/2018/05/grilled-garlic-lemon-herb-salmon-9.jpg").build()));

        dishImageRepository.saveAll(Arrays.asList(
            DishImage.builder().dish(sides1).url("https://kitchenswagger.com/wp-content/uploads/2022/10/parmesan-truffle-fries-4.jpg").build(),
            DishImage.builder().dish(sides1).url("https://diethood.com/wp-content/uploads/2023/02/truffle-fries-7.jpg").build()));

        dishImageRepository.saveAll(Arrays.asList(
            DishImage.builder().dish(sides2).url("https://www.eatwell101.com/wp-content/uploads/2018/02/sauteed-spinach.jpg").build(),
            DishImage.builder().dish(sides2).url("https://www.eatwell101.com/wp-content/uploads/2018/02/sauteed-fresh-spinach.jpg").build()));

        dishImageRepository.saveAll(Arrays.asList(
            DishImage.builder().dish(dessert1).url("https://www.melskitchencafe.com/wp-content/uploads/2023/01/updated-lava-cakes7.jpg").build(),
            DishImage.builder().dish(dessert1).url("https://sallysbakingaddiction.com/wp-content/uploads/2017/02/chocolate-molten-lava-cakes.jpg").build()));

        dishImageRepository.saveAll(Arrays.asList(
            DishImage.builder().dish(dessert2).url("https://www.abakingjourney.com/wp-content/uploads/2022/03/Raspberry-Panna-Cotta-Feature.jpg").build(),
            DishImage.builder().dish(dessert2).url("https://thefoodiephysician.com/wp-content/uploads/2018/05/coconut-panna-cotta-blueberry-sauce1.jpg").build()));
    }
}
