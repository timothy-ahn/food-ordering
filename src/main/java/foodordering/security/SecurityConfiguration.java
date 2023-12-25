package foodordering.security;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .exceptionHandling(conf -> conf.authenticationEntryPoint(http401UnauthorizedEntryPoint))
            .authorizeHttpRequests(req -> req
                .requestMatchers("/api/v1/auth/**")
                    .permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/category", "/api/v1/dish", "/api/v1/menu")
                    .permitAll()
                .requestMatchers("/api/v1/category/admin", "/api/v1/dish/admin", "/api/v1/order/admin")
                    .hasAuthority("ADMIN")
                .anyRequest()
                    .authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
