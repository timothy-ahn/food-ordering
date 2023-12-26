package foodordering.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {
    private List<MenuCategoryDto> categories = new ArrayList<>();
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class MenuCategoryDto {
    private Integer id;
    private String name;
    private List<MenuDishDto> dishes;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class MenuDishDto {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private List<String> images;
}
