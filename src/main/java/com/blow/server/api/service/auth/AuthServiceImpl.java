package com.blow.server.api.service.auth;

import com.blow.server.api.common.message.ExceptionMessage;
import com.blow.server.api.config.SaltEncrypt;
import com.blow.server.api.config.jwt.JwtTokenManager;
import com.blow.server.api.dto.auth.request.LoginRequestDTO;
import com.blow.server.api.dto.auth.request.SignInRequestDTO;
import com.blow.server.api.dto.auth.response.LoginResponseDTO;
import com.blow.server.api.dto.auth.response.SignInResponseDTO;
import com.blow.server.api.entity.User;
import com.blow.server.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final SaltEncrypt saltEncrypt;
    private final JwtTokenManager jwtTokenManager;
    @Override
    @Transactional
    public SignInResponseDTO signIn(SignInRequestDTO request) {
        if (validateEmail(request.email())) {
            throw new EntityNotFoundException(ExceptionMessage.EXIST_EMAIL.getMessage());
        }
        if (validateEmail(request.userName())) {
            throw new IllegalArgumentException(ExceptionMessage.EXIST_USERNAME.getMessage());
        }

        String encryptedPassword = saltEncrypt.createPasswordWithSalt(request.password());

        User user = userRepository.save(request.toEntity(encryptedPassword));

        val userId = user.getId();
        val accessToken = jwtTokenManager.createAccessToken(userId);
        val refreshToken = jwtTokenManager.createRefreshToken(userId);

        user.updateRefreshToken(refreshToken);
        return SignInResponseDTO.of(user,accessToken);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        return null;
    }

    private boolean validateEmail(String email) {
        if (userRepository.existsUserByEmail(email))
            return true;
        return false;
    }

    private boolean validateUserName(String userName) {
        if (userRepository.existsUserByNickname(userName))
            return true;
        return false;
    }
}
