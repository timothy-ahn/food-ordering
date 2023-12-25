package foodordering.dish;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    public List<Dish> findDishesByCategory_Id(Integer categoryId);
}
