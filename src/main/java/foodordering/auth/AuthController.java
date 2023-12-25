package foodordering.auth;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(
        @RequestBody @NotNull RegisterRequest request
    ) {
        if (authenticationService.isUserExists(request.getEmail())){
            AuthResponse response = AuthResponse.builder()
                    .errorCode("InvalidEmail")
                    .errorMessage("Email is already taken!")
                    .build();

            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthResponse> register(
            @RequestBody AuthRequest request
    ) {
        try {
            AuthResponse response = authenticationService.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException ex) {
            AuthResponse response = AuthResponse.builder()
                    .errorCode("InvalidCredentials")
                    .errorMessage("Invalid email or password!")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
