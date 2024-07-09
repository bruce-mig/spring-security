package com.github.bruce_mig.spring_security.basic;

import com.github.bruce_mig.spring_security.security.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.function.Function;

//@Configuration
public class BasicAuthSecurityConfiguration {

    // LDAP or Database
    // InMemoryDatabase

//    @Bean
//    public InMemoryUserDetailsManager createUserDetailsManager(){
//        UserDetails userDetails1 = createNewUser(
//                "admin",
//                "secret",
//                new UserRole[]{UserRole.USER});
//        UserDetails userDetails2 = createNewUser(
//                "user",
//                "password",
//                new UserRole[]{UserRole.USER});
//
//        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
//    }
//
//    private UserDetails createNewUser(String username, String password, UserRole[] role) {
//        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
//        return User.builder()
//                .passwordEncoder(passwordEncoder)
//                .username(username)
//                .password(password)
//                .roles(Arrays.toString(role))
//                .build();
//    }
//    ===================
//    @Bean
//    public UserDetailsService userDetailsService(){
//        var user = User.withUsername("name")
//                .password("{noop}secret")
//                .roles(String.valueOf(UserRole.USER))
//                .build();
//
//        var admin = User.withUsername("admin")
//                .password("{noop}secret")
//                .roles(String.valueOf(UserRole.ADMIN))
//                .build();
//        return new InMemoryUserDetailsManager(user,admin);
//    }

    @Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        var user = User.withUsername("name")
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .password("secret")
                .roles(Arrays.toString(new UserRole[]{UserRole.ZB, UserRole.USER}))
                .build();

        var admin = User.withUsername("admin")
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .password("secret")
                .roles(Arrays.toString(new UserRole[]{UserRole.ADMIN, UserRole.USER}))
                .build();
        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.createUser(user);
        jdbcUserDetailsManager.createUser(admin);

        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
     * Spring security
     *  All URLs are protected
     * A login form is shown for unauthorized users
     * CSRF is disabled
     * disabling csrf requires stateless session
     * Frames (used by h2 console) are disabled
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }
}
