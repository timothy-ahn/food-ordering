package foodordering.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDishesRepository extends JpaRepository<OrderDish, Integer> {
}
