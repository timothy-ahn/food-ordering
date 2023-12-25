package foodordering.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    private String address;
    private String notes;
    private Map<String, Integer> dishes;
}
