package com.dave.springboot.config;

import com.dave.springboot.User.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {

    private User user; // Cái entity từ DB của mày

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Mặc định ép mọi thằng thành quyền "USER" hết
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // Trả pass từ DB
    }

    @Override
    public String getUsername() {
        return user.getUsername(); // Trả tên từ DB
    }

    // Mấy cái dưới này mặc định Spring nó ép phải có, cứ quất true hết để khỏi bị khóa acc
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}