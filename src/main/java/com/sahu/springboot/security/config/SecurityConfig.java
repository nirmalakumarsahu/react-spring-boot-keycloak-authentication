package com.sahu.springboot.security.config;

import com.sahu.springboot.security.config.properties.AppProperties;
import com.sahu.springboot.security.constants.KeycloakConstants;
import com.sahu.springboot.security.constants.RoleConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.cors.*;

import java.util.*;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AppProperties appProperties;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/cars/**").hasRole(RoleConstants.VIEW_CARS.getValue())
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        AppProperties.Cors cors = appProperties.getCors();
        log.info("Cors {}", cors);
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(cors.getUrls());
        config.setAllowedMethods(cors.getMethods());
        config.setAllowedHeaders(cors.getHeaders());
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> authorities = new ArrayList<>();

            Map<String, Object> realmAccess = jwt.getClaim(KeycloakConstants.REALM_ACCESS.getValue());
            if (realmAccess != null && realmAccess.get(KeycloakConstants.ROLES.getValue()) instanceof List<?> roles) {
                log.info("roles 1 {}", roles);
                roles.forEach(role ->
                        authorities.add(new SimpleGrantedAuthority(RoleConstants.ROLE_PREFIX.getValue() + role))
                );
            }

            Map<String, Object> resourceAccess = jwt.getClaim(KeycloakConstants.RESOURCE_ACCESS.getValue());
            if (resourceAccess != null && resourceAccess.get(appProperties.getOauth2ClientName()) instanceof Map<?, ?> client) {
                if (client.get(KeycloakConstants.ROLES.getValue()) instanceof List<?> roles) {
                    log.info("roles 2 {}", roles);
                    roles.forEach(role ->
                            authorities.add(new SimpleGrantedAuthority(RoleConstants.ROLE_PREFIX.getValue() + role))
                    );
                }
            }

            log.info("Authorities: {}", authorities);
            return authorities;
        });

        return converter;
    }

}