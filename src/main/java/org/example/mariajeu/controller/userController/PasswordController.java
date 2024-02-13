package org.example.mariajeu.controller.userController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.mariajeu.dto.userDto.EmailRequest;
import org.example.mariajeu.dto.userDto.UserModifyRequest;
import org.example.mariajeu.dto.userDto.UserPwdChangeRequest;
import org.example.mariajeu.dto.ResponseDTO;
import org.example.mariajeu.exception.AppException;
import org.example.mariajeu.exception.ErrorCode;
import org.example.mariajeu.service.mailService.MailServiceImpl;
import org.example.mariajeu.service.passwordService.PasswordService;
import org.example.mariajeu.service.userService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/find")
public class PasswordController {
    private final PasswordService passwordService;
    private final UserService userService;
    private final MailServiceImpl mailService;

    @PostMapping("/id")
    public ResponseEntity<ResponseDTO> findID(@Valid @RequestBody EmailRequest dto) {
        String userName = passwordService.findID(dto.getEmail());

        return ResponseEntity.ok().body(ResponseDTO.builder()
                .status(HttpStatus.OK)
                .message(userName)
                .build()
        );
    }

    @PostMapping("/password")
    public ResponseEntity<ResponseDTO> findPWD(@Valid @RequestBody EmailRequest dto) {
        passwordService.findPWD(dto.getUserName());

        return ResponseEntity.ok().body(ResponseDTO.builder()
                .status(HttpStatus.OK)
                .message(mailService.joinEmail(dto.getEmail()))
                .build()
        );
    }

    @PatchMapping("/changePW")
    public ResponseEntity<?> changePWD(@Valid @RequestBody UserPwdChangeRequest dto) {
        String userName = passwordService.findID(dto.getEmail());
        passwordService.CheckPassword(userName,dto.getPassword());

        if(!dto.getNewPassword().equals(dto.getNewPasswordAgain()))
            throw new AppException(ErrorCode.BAD_REQUEST, "변경하려는 새 비밀번호의 입력값이 서로 다릅니다.");

        UserModifyRequest userModifyRequest = UserModifyRequest.builder()
                .newPassword(dto.getNewPassword())
                .build();

        userService.modifyUser(userName,userModifyRequest);

        return ResponseEntity.ok().body(userModifyRequest);
    }

}
