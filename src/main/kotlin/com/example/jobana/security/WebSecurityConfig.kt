package com.example.jobana.security


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()
            .authorizeRequests()
            .requestMatchers("/users/**").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/admin").hasRole("ADMIN")
            .requestMatchers("/**").permitAll()
            .and()
            .httpBasic(withDefaults())
        return http.build()
    }


    /*
    @Value("\${app.security.admin_password}")
    val adminPassword: String = ""

    @Bean
    fun userDetailsService(): UserDetailsService {
        val userDetails = User
            .withUsername("user")
            .password("123").passwordEncoder { BCryptPasswordEncoder().encode(it) }
            .roles("USER")
            .build()

        val adminDetails = User
            .withUsername("admin")
            .password(adminPassword).passwordEncoder { BCryptPasswordEncoder().encode(it) }
            .roles("ADMIN")
            .build()
        return InMemoryUserDetailsManager(userDetails, adminDetails)
    }*/
}