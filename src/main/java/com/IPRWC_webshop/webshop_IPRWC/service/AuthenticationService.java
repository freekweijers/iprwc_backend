package com.IPRWC_webshop.webshop_IPRWC.service;

import com.IPRWC_webshop.webshop_IPRWC.dao.UserDAO;
import com.IPRWC_webshop.webshop_IPRWC.model.Role;
import com.IPRWC_webshop.webshop_IPRWC.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Optional<String> register(String username, String password) {
        Optional<User> foundUser = userDAO.findByUsername(username);
        if (foundUser.isPresent()) {
            return Optional.empty();
        }

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .build();

        userDAO.save(user);
        String token = jwtService.generateToken(Map.of("role", user.getRole()), user.getId() );
        return Optional.of(token);
    }

    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        User user = userDAO.loadUserByUsername(username);
        return jwtService.generateToken(Map.of("role", user.getRole()), user.getId());
    }
}
