package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT user_id, pw, active FROM members WHERE user_id=?"
        );

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT user_id, role FROM roles WHERE user_id=?"
        );
        return jdbcUserDetailsManager;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
        try {
            // roles for methods
            httpSecurity.authorizeHttpRequests(configurer ->
                    configurer
                            .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                            .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                            .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
            );

            // basic Auth
            httpSecurity.httpBasic(Customizer.withDefaults());

            // disable CSRF
            httpSecurity.csrf(csrf -> csrf.disable());

            return httpSecurity.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // In-memory roles
    /*@Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        var user1 = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        var user2 = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        var user3 = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }*/
}
