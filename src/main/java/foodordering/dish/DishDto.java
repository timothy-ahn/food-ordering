package foodordering.dish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishDto {
    private Integer id;
    private String name;
    private String description;
    private boolean isEnabled;
    private LocalDateTime createdAt;
    private List<String> images;

    public static DishDto mapFromDish(@NotNull Dish dish) {
        return DishDto.builder()
            .id(dish.getId())
            .name(dish.getName())
            .description(dish.getDescription())
            .images(dish.getDishImages().stream().map(DishImage::getUrl).toList())
            .createdAt(dish.getCreatedAt())
            .isEnabled(dish.isEnabled())
            .build();
    }
}
