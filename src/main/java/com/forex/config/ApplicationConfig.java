package com.forex.config;

import com.forex.dto.UserRequest;
import com.forex.entity.User;
import com.forex.repository.UserRepository;
import com.forex.service.UserDetailService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {
    private static final Logger log = LoggerFactory.getLogger(ApplicationConfig.class);

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailService();
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        UserRequest userRequest = new UserRequest("User", "user@gmail.com", "user@gmail.com", "user", "USER");
        User user = mapper().map(userRequest, User.class);
        user.setPassword(passwordEncoder().encode(userRequest.getPassword()));
        return args -> {
            log.info("Preloading user " + repository.save(user));
        };
    }
}
