package com.blow.server.api.service.auth;

import com.blow.server.api.dto.auth.request.LoginRequestDTO;
import com.blow.server.api.dto.auth.request.SignInRequestDTO;
import com.blow.server.api.dto.auth.response.LoginResponseDTO;
import com.blow.server.api.dto.auth.response.SignInResponseDTO;

public interface AuthService {
    SignInResponseDTO singUp(SignInRequestDTO request);
    LoginResponseDTO login(LoginRequestDTO request);
}
