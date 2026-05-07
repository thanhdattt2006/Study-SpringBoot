package com.dave.springboot.User;

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
    public String getPassword() {
        return user.getPassword(); // Trả pass từ DB
    }

    @Override
    public String getUsername() {
        return user.getUsername(); // Trả tên từ DB
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Đọc cái 'role' của thằng user (ví dụ: "ROLE_ADMIN") và gắn vào thẻ căn cước
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isEnabled() { return true; }
}