package org.example.mariajeu.configuration;

import jakarta.servlet.RequestDispatcher;
import org.example.mariajeu.service.logoutService.LogoutService;
import org.example.mariajeu.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private final LogoutService logoutService;
    private final JwtTokenUtil jwtTokenUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(HttpMethod.POST,"/find/**").permitAll() //아이디 찾기, 비밀번호 찾기, 비밀번호 변경 로직
                        .requestMatchers(HttpMethod.POST,"/users/join","/auth/login").permitAll() //회원가입, 로그인 로직
                        .requestMatchers(HttpMethod.POST,"/mail/**").permitAll() // 이메일 보내기 로직
                        .requestMatchers("/swagger-ui/**","/swagger/**","/swagger-resources/**","/v3/api-docs/**","/").permitAll() //swagger 풀어두기
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtFilter(jwtTokenUtil), UsernamePasswordAuthenticationFilter.class)

//                .logout((logout) -> logout
//                        .logoutUrl("/logout")
//                        .addLogoutHandler(logoutService)
//                        .logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext())))

                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

}
