package org.example.mariajeu.controller.userController;

import org.example.mariajeu.dto.userDto.TokenDto;
import org.example.mariajeu.service.authService.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(HttpServletRequest request, HttpServletResponse response) {
        String authorization = request.getHeader("Authorization");
        String refreshToken = authorization.split(" ")[1];
        String newAccessToken = authService.reissue(refreshToken);
        //response.setHeader("Authorization", "Bearer " + newAccessToken);
        TokenDto tokenDto = new TokenDto(newAccessToken,null);
        return ResponseEntity.ok().body(tokenDto);
    }



}
