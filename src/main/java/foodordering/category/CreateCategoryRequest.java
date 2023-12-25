package foodordering.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {
    private String name;
    private Integer order;
    private Boolean isEnabled;
}
