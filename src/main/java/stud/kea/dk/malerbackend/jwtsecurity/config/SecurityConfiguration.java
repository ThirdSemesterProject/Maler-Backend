package stud.kea.dk.malerbackend.jwtsecurity.config;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import stud.kea.dk.malerbackend.jwtsecurity.JwtAuthenticationEntryPoint;
import stud.kea.dk.malerbackend.jwtsecurity.JwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration implements WebMvcConfigurer {

    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtFilter jwtFilter;

    // PasswordEncoder Bean til kryptering af adgangskoder
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // SecurityFilterChain til at konfigurere HTTP-sikkerhed
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors() // Aktiver CORS
                .and()
                .csrf().disable() // Deaktiver CSRF (ikke nødvendigt for REST API'er)
                .authorizeHttpRequests()
                .requestMatchers(
                        "/api/**", // Tillad alle endpoints under /api
                        "/login", // Tillad login-endpoint
                        "/signup" // Tillad signup-endpoint
                ).permitAll()
                .anyRequest().authenticated() // Alle andre forespørgsler kræver authentication
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint) // Håndter uautoriserede forespørgsler
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Brug stateless sessioner

        // Tilføj JWT-filter til at validere tokens
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Bean til AuthenticationManager, som bruges til at håndtere authentication
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Konfiguration af CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Tillad alle endpoints
                .allowedOriginPatterns("https://*.github.io") // Tillad alle subdomæner under GitHub Pages
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // Tillad specifikke HTTP-metoder
                .allowCredentials(true) // Tillad credentials som cookies og authorization headers
                .allowedHeaders("*") // Tillad alle headers
                .exposedHeaders("Authorization", "Content-Type"); // Eksponer disse headers for klienten
        System.out.println("CORS Configuration Applied");
    }
}