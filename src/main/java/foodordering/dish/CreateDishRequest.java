package foodordering.dish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDishRequest {
    private String name;
    private String description;
    private Integer categoryId;
    private List<String> images;
}
