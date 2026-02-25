package com.tutorial.portal.config;

import com.tutorial.portal.entity.AppUser;
import com.tutorial.portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultUserSeeder implements CommandLineRunner {

    public static final String TEST_USERNAME = "testuser";
    public static final String TEST_PASSWORD = "Test@1234";
    private static final String TEST_ROLE = "ROLE_USER";
    private static final Logger logger = LoggerFactory.getLogger(DefaultUserSeeder.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername(TEST_USERNAME).isPresent()) {
            return;
        }

        AppUser user = new AppUser();
        user.setUsername(TEST_USERNAME);
        user.setPassword(passwordEncoder.encode(TEST_PASSWORD));
        user.setRole(TEST_ROLE);
        userRepository.save(user);

        logger.info("Seeded default test user: username='{}', role='{}'", TEST_USERNAME, TEST_ROLE);
    }
}
