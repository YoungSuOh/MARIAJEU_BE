package org.example.mariajeu.service.userService;

import org.example.mariajeu.exception.AppException;
import org.example.mariajeu.exception.ErrorCode;
import org.example.mariajeu.repository.userRepository.JwtRepository;
import org.example.mariajeu.utils.JwtTokenUtil;
import org.example.mariajeu.utils.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogoutService implements LogoutHandler {

    private final JwtRepository jwtRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final RedisUtil redisUtil;


    @Transactional
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String authorization = request.getHeader("Authorization");

        if (authorization == null)
            throw new AppException(ErrorCode.UNAUTHORIZED,"인증 정보가 유효하지 않습니다.");

        String token = authorization.split(" ")[1];

        if (!jwtTokenUtil.validateToken(token)) {
            throw new AppException(ErrorCode.UNAUTHORIZED,"접근이 금지된 토큰입니다.");
        }

        String userName = jwtTokenUtil.getUserName(token);
        jwtRepository.deleteByUserName(userName);

        Long expiration = jwtTokenUtil.getExpiration(token);
        redisUtil.setBlackList(token, "access_token", expiration);

    }
}
