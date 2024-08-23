package cl.api.apiuser.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * Configures Spring Security's HTTP request authorization.
     *
     * @param httpSecurity HttpSecurity object for customization
     * @return SecurityFilterChain Spring Security filterChain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        //USER CONTROLLER
                        .requestMatchers("/api-user/**").hasRole("ADMIN")
                        .requestMatchers("/api-user/api/v1/user/hearthbeat").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api-user/api/v1/user/login").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api-user/api/v1/user/registro").hasRole("ADMIN")
                        //H2 DATABASE
                        .requestMatchers("/api-user/h2-console").hasRole("ADMIN")
                        .requestMatchers("/api-user/h2-console/**").hasRole("ADMIN")
                        //LOGIN
                        .requestMatchers("/api-user/login/").hasAnyRole("ADMIN", "USER")
                        .anyRequest()
                        .authenticated()
                )
                .headers(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    /**
     * Creates an in-memory user details service with
     * predefined users.
     *
     * @return UserDetailsService with test users
     */
    @Bean public UserDetailsService users()
    {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users.username("user")
                .password("test123")
                .roles("USER")
                .build();
        UserDetails admin = users.username("admin")
                .password("test123")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
