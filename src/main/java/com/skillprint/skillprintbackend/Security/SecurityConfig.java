package com.skillprint.skillprintbackend.Security;


import com.skillprint.skillprintbackend.Security.Filters.JwtAuthenticationFilter;
import com.skillprint.skillprintbackend.Security.Filters.JwtAuthorizationFilter;
import com.skillprint.skillprintbackend.Security.Jwt.JwtUtils;
import com.skillprint.skillprintbackend.Service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final JwtUtils jwtUtils;

    private  final UserDetailsServiceImpl userDetailsService;

    private  final JwtAuthorizationFilter authorizationFilter;

    public SecurityConfig(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService, JwtAuthorizationFilter authorizationFilter) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.authorizationFilter = authorizationFilter;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception{ //filtros de seguridad

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return httpSecurity
                .csrf(config -> config.disable())
                .authorizeHttpRequests( auth -> {
                    auth.requestMatchers("/api/employed/register").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilter(jwtAuthenticationFilter)      //.httpBasic()
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();                               //.and()

    }

/*    @Bean
    UserDetailsService userDetailsService(){

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("Santiago")
                .password("1234")
                .roles()
                .build()
        );
        return manager;
    } */

    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder(); //NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }



}
