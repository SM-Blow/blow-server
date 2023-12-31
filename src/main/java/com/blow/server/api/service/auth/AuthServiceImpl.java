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
    public SignInResponseDTO singUp(SignInRequestDTO request) {
        if (validateEmail(request.email())) {
            throw new EntityNotFoundException(ExceptionMessage.EXIST_EMAIL.getMessage());
        }
        if (validateUserName(request.userName())) {
            throw new IllegalArgumentException(ExceptionMessage.EXIST_USERNAME.getMessage());
        }


        String encryptedPassword = saltEncrypt.createPasswordWithSalt(request.password());

        val user = userRepository.save(request.toEntity(encryptedPassword));

        val userId = user.getId();
        val accessToken = jwtTokenManager.createAccessToken(userId);
        val refreshToken = jwtTokenManager.createRefreshToken(userId);

        user.updateRefreshToken(refreshToken);
        user.updateFCMToken(request.fcmDeviceToken());
        return SignInResponseDTO.of(user,accessToken);
    }

    @Transactional
    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        if (!validateEmail(request.email()))
            throw new EntityNotFoundException(ExceptionMessage.INVALID_EMAIL.getMessage());

        val user = checkPassword(request.email(), request.password());
        val userId = user.getId();

        val accessToken = jwtTokenManager.createAccessToken(userId);
        val refreshToken = jwtTokenManager.createRefreshToken(userId);
        user.updateRefreshToken(refreshToken);
        user.updateFCMToken(request.fcmDeviceToken());

        return LoginResponseDTO.of(user,accessToken);
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

    private User checkPassword(String email, String password) {
        val user = userRepository.findByEmail(email);
        if (saltEncrypt.isMatch(password, user.getPassword()))
            return user;
        else
            throw new IllegalArgumentException(ExceptionMessage.INVALID_PASSWORD.getMessage());
    }
}
