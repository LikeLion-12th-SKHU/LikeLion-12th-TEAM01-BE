package org.skhuton.fitpete.auth.global.controller;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.auth.dto.Token;
import org.skhuton.fitpete.auth.global.service.AuthLoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login/oauth2")
public class AuthLoginController {

    private final AuthLoginService authLoginService;

//    // 콜솔에 출력
//    @GetMapping("/code/{registrationId}")
//    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
//        authLoginService.socialLogin(code, registrationId);
//    }

    @GetMapping("code/google")
    public Token googleCallback(@RequestParam(name = "code") String code) {
        String googleAccessToken = authLoginService.getGoogleAccessToken(code);
        return loginOrSignUp(googleAccessToken);
    }

    public Token loginOrSignUp(String googleAccessToken) { // 인가 코드를 통해 로그인이나 회원가입 하도록 함
        return authLoginService.loginOrSignUp(googleAccessToken);
    }
}
