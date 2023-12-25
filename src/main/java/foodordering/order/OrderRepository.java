package foodordering.order;

import foodordering.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    public List<Order> getOrdersByUser(User user);
}
