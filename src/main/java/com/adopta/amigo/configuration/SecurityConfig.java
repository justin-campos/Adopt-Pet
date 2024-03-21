package com.adopta.amigo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.adopta.amigo.services.CustomUserDetailService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    String[] resources = new String[]{
            "/css/",
            "/js/",
    };

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                                .requestMatchers(resources).permitAll()
                                .requestMatchers("/login", "registro").permitAll()
                                .requestMatchers("/").hasAnyAuthority("ROLE_SUPER_ADMIN","ROLE_USER")// Permite el acceso a la ruta de inicio de sesión
                                .requestMatchers("/admin").hasAnyAuthority("ROLE_SUPER_ADMIN")// Permite el acceso a la ruta de inicio de sesión
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .loginProcessingUrl("/login")// Utiliza tu ruta de inicio de sesión personalizada
                                .usernameParameter("name")
                                .passwordParameter("hash")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout") // URL para iniciar el logout
                                .logoutSuccessUrl("/login?logout") // URL a la que se redirige después del logout
                                .invalidateHttpSession(true) // Invalida la sesión HTTP existente
                                .deleteCookies("JSESSIONID") // Elimina las cookies
                );

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService);
    }




}

