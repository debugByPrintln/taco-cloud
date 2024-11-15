package com.example.tacocloud.security;

import com.example.tacocloud.data.Users;
import com.example.tacocloud.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
public class SecurityConfiguration{

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UsersRepository userRepo) {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                log.info("USERNAME OF USER TO BE FOUND: " + username);
                Users user = userRepo.findByUsername(username);
                if (user != null) {
                    log.info("USER OBJECT CREATED IS: " + user);
                    log.info("GOT USER FROM DB WITH USERNAME: " + user.getUsername() + " AND PASSWORD: " + user.getPassword());
                    return user;
                }
                else{
                    log.info("NO USER WAS FOUND IN DB WITH USERNAME: " + username);
                    throw new UsernameNotFoundException("User ‘" + username + "’ not found");
                }
            }
        };
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .requestMatchers("/design", "/orders")
                    .hasRole("USER")
                .requestMatchers("/", "/**")
                    .permitAll().anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                            .defaultSuccessUrl("/", true)
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .csrf()
                        .disable() // Отключение CSRF-защиты для отладки
                .headers()
                    .frameOptions()
                        .sameOrigin()
                .and()
                .build();
    }
}
