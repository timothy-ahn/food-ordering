package foodordering.dish;

import foodordering.category.Category;
import foodordering.order.Order;
import foodordering.order.OrderDish;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private boolean isEnabled;
    private double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<DishImage> dishImages;

    @OneToMany(mappedBy = "dish")
    private List<OrderDish> orderDishes;
}
