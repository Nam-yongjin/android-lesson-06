package kr.easw.lesson06.controller;

import kr.easw.lesson06.model.dto.ExceptionalResultDto;
import kr.easw.lesson06.model.dto.UserDataEntity;
import kr.easw.lesson06.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.datatransfer.DataFlavor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserAuthEndpoint {
    private final UserDataService userDataService;

    public UserAuthEndpoint(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    // JWT 인증을 위해 사용되는 엔드포인트입니다.
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserDataEntity entity) {
        try {
            // 로그인을 시도합니다.
            return ResponseEntity.ok(userDataService.createTokenWith(entity));
        } catch (Exception ex) {
            // 만약 로그인에 실패했다면, 400 Bad Request를 반환합니다.
            return ResponseEntity.badRequest().body(new ExceptionalResultDto(ex.getMessage()));
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@ModelAttribute UserDataEntity entity) {
        // 유저 회원가입을 구현하십시오.
        // 해당 메서드를 작성하기 위해서는, UserDataService와 admin_dashboard.html을 참고하십시오.
        // 해당 메서드는 register.html에서 사용됩니다.
        try {

            DataFlavor request = null;
            String userId = request.getParameter("username");
            String password = request.getParameter("password");

            // 유저 회원가입을 처리합니다.
            UserDataEntity newUser = new UserDataEntity(0L, userId, password, false);
            userDataService.createUser(newUser); // 회원가입 완료

            return ResponseEntity.ok("User registered successfully");
        } catch (Exception ex) {
            // 회원가입에 실패했다면, 400 Bad Request를 반환합니다.
            return ResponseEntity.badRequest().body("Registration failed: " + ex.getMessage());
        }
    }
}
