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
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private JwtFilter filter;
    private static PasswordEncoder passwordEncoder;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = new BCryptPasswordEncoder();
        }
        return passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/api/customer",
                        "/api/shop/**",
                        "/api/upload",
                        "/api/upload/",
                        "/api/upload/**",
                        "/api/colors/fetch",
                        "/api/paint/getAllPaints",
                        "/api/search",
                        "/api/products",
                        "/api/cart/**",
                        "/api/products/*",
                        "/api/order-details/*",
                        "/api/order-items/*",
                        "/api/orders/*",
                        "/api/customer/*",
                        "/api/customer/CustomerById/*",
                        "/api/orders/status/*",
                        "/api/orders/*/status",
                        "/api/orders/*/*",
                        "/api/orders/{id}/status",
                        "/api/**",
                        "/api/orders/*",
                        "/api/orders/**"
                ).permitAll()
                .requestMatchers("/login", "/signup").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://thirdsemesterproject.github.io") // Tillad dit GitHub Pages-domæne
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowCredentials(true)
                .allowedHeaders("*")
                .exposedHeaders("Authorization", "Content-Type");
        System.out.println("CORS Configuration Applied");
    }
}

