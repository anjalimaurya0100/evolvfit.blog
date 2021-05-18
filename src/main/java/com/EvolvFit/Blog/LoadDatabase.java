package com.evolvfit.blog;

import com.evolvfit.blog.model.User;
import com.evolvfit.blog.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            User user = new User("Bilbo", "Baggins");
            log.info("Preloading " + repository.save(user));
        };
    }
}
