package com.IPRWC_webshop.webshop_IPRWC.seeder;

import com.IPRWC_webshop.webshop_IPRWC.dao.UserDAO;
import com.IPRWC_webshop.webshop_IPRWC.model.Role;
import com.IPRWC_webshop.webshop_IPRWC.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminSeeder {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger;

    @Value("${super-admin.name}")
    private String adminName;

    @Value("${super-admin.password}")
    private String adminPassword;

    public void seed() {
        var admin = User.builder()
                .username(adminName)
                .password(passwordEncoder.encode(adminPassword))
                .role(Role.ADMIN)
                .build();
        try {
            this.userDAO.save(admin);
        } catch (Exception e) {
            logger.warn("couldn't create admin account: " + e.getMessage());
        }
    }
}