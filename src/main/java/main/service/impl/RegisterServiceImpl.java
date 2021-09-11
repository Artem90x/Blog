package main.service.impl;

import main.api.request.RegisterRequest;
import main.api.response.RegisterErrorResponse;
import main.api.response.RegisterResponse;
import main.repository.CaptchaRepository;
import main.repository.UserRepository;
import main.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CaptchaRepository captchaRepository;

    @Override
    public RegisterResponse checkRegister(RegisterRequest registerRequest) {
        boolean result = true;
        RegisterErrorResponse registerErrorResponse = new RegisterErrorResponse();

        if (registerRequest.getPassword().length() < 6) {
            registerErrorResponse.setPassword();
            result = false;
        }
        if (registerRequest.getE_mail().equals(userRepository.findUserByEmail(registerRequest.getE_mail()))) {
            registerErrorResponse.setEmail();
            result = false;
        }
        if (!(registerRequest.getCaptcha().equals(captchaRepository.checkCaptcha(registerRequest.getCaptcha_secret())))) {
            registerErrorResponse.setCaptcha();
            result = false;
        }
        if (!registerRequest.getName().matches("[\\w]+")) {
            registerErrorResponse.setName();
            result = false;
        }

        if (!result) {
            return new RegisterResponse(false, registerErrorResponse);
        } else {
            userRepository.insertUser(registerRequest.getE_mail(), registerRequest.getName(), registerRequest.getPassword(),
                    LocalDate.now());
            return new RegisterResponse(true);
        }
    }
}
