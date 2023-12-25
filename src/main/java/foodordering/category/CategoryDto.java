package foodordering.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private Integer id;
    private String name;
    private Integer order;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CategoryDto mapFromCategory(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .order(category.getOrderPos())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}
