package foodordering.order;

import foodordering.dish.Dish;
import foodordering.dish.DishRepository;
import foodordering.user.User;
import foodordering.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;
    private final OrderDishesRepository orderDishesRepository;

    @Transactional
    public Order create(@NotNull UserDetails userDetails, @NotNull CreateOrderRequest request) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        Order order = Order.builder()
            .user(user)
            .address(request.getAddress())
            .notes(request.getNotes())
            .createdAt(LocalDateTime.now())
            .build();
        List<OrderDish> orderDishes = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : request.getDishes().entrySet()) {
            Dish dish = dishRepository.findById(Integer.parseInt(entry.getKey())).orElseThrow();
            orderDishes.add(OrderDish.builder()
                .dish(dish)
                .price(dish.getPrice())
                .count(entry.getValue())
                .order(order)
                .build());
        }

        order.setTotal(orderDishes.stream().mapToDouble(od -> od.getCount() * od.getPrice()).sum());

        orderRepository.save(order);
        orderDishesRepository.saveAll(orderDishes);

        return order;
    }

    public List<Order> getAll(@NotNull UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        return orderRepository.getOrdersByUser(user);
    }
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
