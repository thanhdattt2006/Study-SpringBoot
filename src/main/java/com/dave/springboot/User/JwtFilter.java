package com.dave.springboot.User;

import com.dave.springboot.config.MyUserDetailsService;
import io.jsonwebtoken.security.Password;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        // kiểm tra header có chứa bearer token hay không
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        // nếu có username và chưa đc xác thực trong SecurityContext
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
            System.out.println("DEBUG: Tim thay User: " + userDetails.getUsername());

            // nếu token hợp lệ, set quyền cho Spring Security
            if (jwtService.validateToken(token, userDetails)) {
                System.out.println("DEBUG: Token hop le!");
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }   else {
                System.out.println("DEBUG: Token bi sai hoac het han!");
            }
        }

        // request đi tiếp tới Filter tiếp theo hoặc tới Controller
        filterChain.doFilter(request, response);
    }
}
