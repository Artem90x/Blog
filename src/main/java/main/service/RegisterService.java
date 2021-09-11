package main.service;


import main.api.request.RegisterRequest;
import main.api.response.RegisterResponse;

public interface RegisterService {

    RegisterResponse checkRegister(RegisterRequest registerRequest);
}
