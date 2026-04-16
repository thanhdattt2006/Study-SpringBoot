package com.dave.springboot.User;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User register (User user) {
        // pass cũ -> mã hoá -> ghi đè pass mới
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    public String verify(User user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername()); // Trả về chuỗi Token cho Client
        } else {
            return "Thất bại rồi con trai!";
        }
    }
}
