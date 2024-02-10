package org.example.mariajeu.controller.userController;

import org.example.mariajeu.dto.userDto.EmailCheck;
import org.example.mariajeu.dto.userDto.EmailRequest;
import org.example.mariajeu.exception.AppException;
import org.example.mariajeu.exception.ErrorCode;
import org.example.mariajeu.service.userService.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    @PostMapping("/Send")
    public ResponseEntity<?> mailSend(@RequestBody @Valid EmailRequest emailDto) {
        System.out.println("이메일 인증 이메일 :" + emailDto.getEmail());

        Map<String, Object> resp = new HashMap<>();
        resp.put("status",200);
        resp.put("contents",mailService.joinEmail(emailDto.getEmail()));

        return ResponseEntity.ok(resp);
    }

    @PostMapping("/AuthCheck")
    public ResponseEntity<?> AuthCheck(@RequestBody @Valid EmailCheck emailCheckDto){
        Boolean Checked=mailService.CheckAuthNum(emailCheckDto.getEmail(),emailCheckDto.getAuthNum());
        Map<String, Object> resp = new HashMap<>();
        resp.put("status",200);
        resp.put("contents","인증이 완료되었습니다");
        if(Checked){
            return ResponseEntity.ok(resp);
        }
        else{
            throw new AppException(ErrorCode.BAD_REQUEST,"인증 번호가 맞지 않습니다.");
        }
    }

}