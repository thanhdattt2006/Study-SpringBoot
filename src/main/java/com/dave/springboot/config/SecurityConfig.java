package com.dave.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 1. TẮT MẸ CÁI CSRF ĐI: Không thèm xài Token chống hack rườm rà nữa (phục vụ cho API)
        http.csrf(customizer -> customizer.disable());

        // 2. ÉP XÁC THỰC MỌI LỖ HỔNG: Thằng nào gọi bất kỳ API nào cũng phải trình thẻ ra
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());

        // 3. CHO PHÉP TEST QUA POSTMAN (Basic Auth)
        // Thay vì văng ra cái form HTML đăng nhập ghẻ lở, nó sẽ nhận chữ Basic Auth trên Postman
        http.httpBasic(Customizer.withDefaults());

        // 4. BIẾN SERVER THÀNH THẰNG MẤT TRÍ NHỚ (Stateless)
        // Cứ mỗi lần m gọi API, server đéo thèm nhớ mày là ai (đéo xài Session ID nữa).
        // Mày phải khai báo tài khoản MỖI LẦN gửi request.
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build(); // Lắp ráp xong cái khiên, xuất xưởng!
    }
}