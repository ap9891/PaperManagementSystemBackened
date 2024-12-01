//package com.fantasy.packaging.backend.config;
//
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.web.cors.CorsConfiguration;
////import org.springframework.web.cors.CorsConfigurationSource;
////import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
////
////import java.util.Arrays;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig {
////
////  @Bean
////  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////    http
////        .csrf().disable()
////        .cors().configurationSource(corsConfigurationSource())
////        .and()
////        .authorizeRequests()
////        .antMatchers("/api/**").permitAll()
////        .anyRequest().authenticated();
////
////    return http.build();
////  }
////
////  @Bean
////  public CorsConfigurationSource corsConfigurationSource() {
////    CorsConfiguration configuration = new CorsConfiguration();
////    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
////    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
////    configuration.setAllowedHeaders(Arrays.asList("*"));
////    configuration.setAllowCredentials(true);
////
////    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////    source.registerCorsConfiguration("/**", configuration);
////    return source;
////  }
////}
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http
//        .csrf(csrf -> csrf.disable())  // Disable CSRF for REST APIs
//        .sessionManagement(session ->
//            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        )
//        .authorizeHttpRequests(auth ->
//            auth
//                .antMatchers("/api/health").permitAll()  // Allow health endpoint
//                .antMatchers("/api/public/**").permitAll()  // Allow other public endpoints
//                .antMatchers("/api/auth/**").permitAll()   // Allow authentication endpoints
//                .anyRequest().authenticated()  // Require authentication for all other requests
//        )
//        .formLogin(form -> form.disable())  // Disable form login
//        .httpBasic(basic -> basic.disable());  // Disable basic auth
//
//    return http.build();
//  }
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//}
package com.fantasy.packaging.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .antMatchers("/api/auth/**").permitAll()
            .antMatchers("/api/paper-master/**").permitAll()
            .antMatchers("/api/shades/**").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(form -> form.disable())
        .httpBasic(basic -> basic.disable());

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3001"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}