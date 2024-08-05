package org.skhuton.fitpete.auth.global.controller;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.auth.dto.Token;
import org.skhuton.fitpete.auth.global.service.AuthLoginService;
import org.skhuton.fitpete.auth.global.template.ResponseTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login/oauth2")
public class AuthLoginController {

    private final AuthLoginService authLoginService;

    /*
    // 콜솔에 출력
    @GetMapping("/code/{registrationId}")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
        authLoginService.socialLogin(code, registrationId);
    }
     */
    @GetMapping("/google")
    public void googleLogin() throws IOException {
        authLoginService.socialLogin();
    }

    @GetMapping("code/google") // 인가 코드 받음
    public ResponseTemplate<Token> googleCallback(@RequestParam(name = "code") String code) {
        String decode = URLDecoder.decode(code, StandardCharsets.UTF_8);

        String googleAccessToken = authLoginService.getGoogleAccessToken(decode);
        return loginOrSignUp(googleAccessToken);
    }

    public ResponseTemplate<Token> loginOrSignUp(String googleAccessToken) { // 인가 코드를 통해 로그인이나 회원가입 하도록 함
        return authLoginService.loginOrSignUp(googleAccessToken);
    }
}
