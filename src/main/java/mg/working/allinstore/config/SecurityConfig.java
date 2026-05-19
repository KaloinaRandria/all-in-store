package mg.working.allinstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Désactive CSRF (pas nécessaire pour une API REST)
                .csrf(csrf -> csrf.disable())

                // Configuration des autorisations
                .authorizeHttpRequests(auth -> auth
                                // Permettre l'accès à TOUS les endpoints (temporaire pour le développement)
                                .requestMatchers("/**").permitAll()
                        // Plus tard, nous protégerons les endpoints comme ceci :
                        // .requestMatchers("/api/public/**").permitAll()
                        // .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        // .anyRequest().authenticated()
                )

                // Pas de session (stateless car on utilise JWT)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}