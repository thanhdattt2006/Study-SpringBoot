package com.dave.springboot.config;

import com.dave.springboot.User.User;
import com.dave.springboot.User.UserPrincipal;
import com.dave.springboot.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);

        if (user == null) {
            System.out.println("Đéo thấy thằng này trong DB!");
            throw new UsernameNotFoundException("User not found");
        }

        // Trả về một đối tượng bọc cái User lại (xem bước 5)
        return new UserPrincipal(user);
    }
}