package main.controller;

import lombok.AllArgsConstructor;
import main.api.request.RegisterRequest;
import main.api.response.AuthCaptchaResponse;
import main.api.response.LoginResponse;
import main.api.response.RegisterResponse;
import main.service.impl.CaptchaServiceImpl;
import main.service.impl.RegisterServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class ApiAuthController {

    private final CaptchaServiceImpl captchaService;
    private final RegisterServiceImpl registerService;

    @GetMapping("/check")
    public ResponseEntity<LoginResponse> check(Principal principal) {
        if (principal == null) {
            return ResponseEntity.ok(new LoginResponse());
        }
        return null;
    }

    @GetMapping("/captcha")
    public AuthCaptchaResponse getCaptcha() throws IOException {

        return captchaService.getCaptcha();
    }

    @PostMapping("/register")
    public RegisterResponse checkRegister(
            @RequestBody RegisterRequest registerRequest) {

        return registerService.checkRegister(registerRequest);
    }
}
