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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
//                )
//                .headers(headers -> headers.frameOptions(frameOptionsConfig -> headers.disable()))
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")));
//        return httpSecurity.build();
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity.csrf(csrf -> csrf
//                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/api-user/api/v1/user/hearthbeat",
//                                "/api-user/api/v1/user/registro",
//                                "/h2-console/**",
//                                "/login").permitAll()
//                ).formLogin(Customizer.withDefaults()).build();
//    }

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
                        .requestMatchers("/api-user/api/v1/user/registro").hasRole("ADMIN")
                        //H2 DATABASE
                        .requestMatchers("/api-user/h2-console/**").hasRole("ADMIN")
                        //LOGIN
                        .requestMatchers("/api-user/login/").hasRole("ADMIN")
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
