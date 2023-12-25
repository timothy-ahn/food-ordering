package foodordering.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequest {
    private Integer id;
    private String name;
    private Integer order;
    private Boolean isEnabled;
}
