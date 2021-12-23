package com.example.springboot.secyrity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler; // класс, в котором описана логика перенаправления пользователей по ролям

    public SecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.csrf().disable(); - попробуйте выяснить сами, что это даёт
        http.authorizeRequests()
                .antMatchers("/").permitAll() // доступность всем
                .antMatchers("/open").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')") // разрешаем входить на /user пользователям с ролью User
                .antMatchers("/admin", "/update/**", "/delete/**", "/form", "/create").access("hasAnyRole('ROLE_ADMIN')")// разрешаем входить на /user пользователям с ролью Admin
                .antMatchers("/user").access("hasAnyRole('ROLE_USER')")
                .and().formLogin()  // Spring сам подставит свою логин форму
                .successHandler(successUserHandler).and().exceptionHandling().accessDeniedPage("/access_denied"); // подключаем наш SuccessHandler для перенеправления по ролям
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MessageDigestPasswordEncoder("MD5");


    }
}