package com.dave.springboot.User;

import org.springframework.beans.factory.annotation.Autowired;
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
}
