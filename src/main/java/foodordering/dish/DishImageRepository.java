package foodordering.dish;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DishImageRepository extends JpaRepository<DishImage, Integer> {
    public void deleteAllByDish_Id(Integer dishId);
}
