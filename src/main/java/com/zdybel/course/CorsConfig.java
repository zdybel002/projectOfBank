package com.zdybel.course;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Oznacza, że klasa dostarcza konfigurację dla kontenera Spring
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 1. Zastosowanie do wszystkich ścieżek w aplikacji
                .allowedOrigins("http://localhost:3000") // 2. Tylko dla frontendu React (port 3000)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 3. Dozwolone metody (OPTIONS dla pre-flight)
                .allowedHeaders("*") // 4. Zezwolenie na wszystkie nagłówki
                .allowCredentials(true) // 5. Zezwolenie na ciasteczka/nagłówki autoryzacji (jeśli używasz)
                .maxAge(3600); // Czas ważności dla odpowiedzi pre-flight (1 godzina)
    }
}
