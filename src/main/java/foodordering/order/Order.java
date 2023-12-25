package foodordering.order;

import foodordering.user.User;
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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String address;
    private String notes;
    private double total;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderDish> orderDishes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
