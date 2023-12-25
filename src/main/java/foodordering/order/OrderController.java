package foodordering.order;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Integer> create(
            @AuthenticationPrincipal @NotNull UserDetails userDetails,
            @RequestBody @NotNull CreateOrderRequest request
    ){
        return ResponseEntity.ok(orderService.create(userDetails, request).getId());
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll(
            @AuthenticationPrincipal @NotNull UserDetails userDetails
    ) {
        return ResponseEntity.ok(
            orderService.getAll(userDetails).stream().map(order ->
                OrderDto.builder()
                    .address(order.getAddress())
                    .total(order.getTotal())
                    .notes(order.getNotes())
                    .dishes(order.getOrderDishes().stream().map(orderDish ->
                        OrderDishDto.builder()
                            .name(orderDish.getDish().getName())
                            .description(orderDish.getDish().getDescription())
                            .price(orderDish.getPrice())
                            .build()
                )
                .toList())
            .build())
            .toList()
        );
    }

    @GetMapping("admin")
    public ResponseEntity<List<OrderDto>> getAll() {
        return ResponseEntity.ok(
            orderService.getAll().stream().map(order ->
                OrderDto.builder()
                    .address(order.getAddress())
                    .total(order.getTotal())
                    .notes(order.getNotes())
                    .dishes(order.getOrderDishes().stream().map(orderDish ->
                        OrderDishDto.builder()
                            .name(orderDish.getDish().getName())
                            .description(orderDish.getDish().getDescription())
                            .price(orderDish.getPrice())
                            .build()
                    )
                    .toList())
                    .build())
            .toList()
        );
    }
}
