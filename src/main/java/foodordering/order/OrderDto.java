package foodordering.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer id;
    private String address;
    private String notes;
    private Double total;
    private List<OrderDishDto> dishes;

}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class OrderDishDto {
    private String name;
    private String description;
    private Double price;
}
