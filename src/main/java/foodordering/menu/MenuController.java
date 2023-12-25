package foodordering.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuController  {
    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<MenuDto> getMenu() {
        return ResponseEntity.ok(menuService.getMenu());
    }
}
