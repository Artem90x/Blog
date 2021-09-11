package main.service;

import main.api.response.AuthCaptchaResponse;

import java.io.IOException;

public interface CaptchaService {

    AuthCaptchaResponse getCaptcha() throws IOException;
}
