package org.example.mariajeu.controller.userController;

import org.example.mariajeu.domain.userDomain.Role;
import org.example.mariajeu.dto.userDto.*;
import org.example.mariajeu.exception.AppException;
import org.example.mariajeu.exception.ErrorCode;
import org.example.mariajeu.service.userService.LoginService;
import org.example.mariajeu.service.userService.LogoutService;
import org.example.mariajeu.service.userService.MailService;
import org.example.mariajeu.service.userService.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final LogoutService logoutService;
    private final LoginService loginService;
    private final MailService mailService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserJoinRequest dto) {
        if (!dto.isAgreedToTerms1() || !dto.isAgreedToTerms2())
            throw new AppException(ErrorCode.BAD_REQUEST, "Terms agreement is required.");


        boolean isAuthVerified = mailService.CheckAuthNum(dto.getEmail(), dto.getAuthNum());
        if (!isAuthVerified)
            throw new AppException(ErrorCode.BAD_REQUEST, "Email verification failed.");

        userService.join(dto);

        mailService.DeleteAuthNum(dto.getAuthNum());

    return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest dto){
        TokenDto token = loginService.login(dto.getUserName(), dto.getPassword());

        return ResponseEntity.ok().body(token);
    }

//    @ResponseBody
//    @PostMapping("/kakao/login")
//    public ResponseEntity<?> kakaoLogin(@RequestParam(required = false) String code){
//        String accessToken = loginService.getKakaoAccessToken(code);
//        System.out.println(accessToken);
//    }

    @PatchMapping("/modify")
    public ResponseEntity<?> modify(@RequestBody UserModifyRequest dto, Authentication authentication) {
        String userName = authentication.getName();
        if(!userService.CheckPassword(userName,dto.getPassword()))
            throw new AppException(ErrorCode.BAD_REQUEST, "Invalid password.");

        userService.modifyUser(userName,dto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam(name = "search") String target, Authentication authentication){
        // all 모두 (관리자권한만) //유저이름 유저데이터 반환 //빈칸이거나 공백이면 오류반환
        String reqUser = authentication.getName();
        UserListResponse user = userService.getUser(reqUser);
        if(target.equals("All")) {
            if (user.getRole().equals(Role.ADMIN))
                return ResponseEntity.ok().body(userService.getALL());
            else
                throw new AppException(ErrorCode.BAD_REQUEST, "you don't currently have permission to access this.");
        }
        else{
            UserListResponse respUser = userService.getUser(target);
            return ResponseEntity.ok().body(respUser);
        }
    }

    @DeleteMapping("deleteUser/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable("userName") String userName, Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        String reqUser = authentication.getName();
        Role targetRole = userService.getUser(reqUser).getRole();
        Map<String, Object> resp = new HashMap<>();

        if (targetRole.equals(Role.ADMIN) || reqUser.equals(userName)) {
            userService.deleteUser(userName);
            logoutService.logout(request, response, authentication);
            resp.put("status",200);
            resp.put("contents","유저 삭제가 완료되었습니다");
            return ResponseEntity.ok(resp);
        }
        else
            throw new AppException(ErrorCode.BAD_REQUEST, "you don't currently have permission to access this.");
    }


}
