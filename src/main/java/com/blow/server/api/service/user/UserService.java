package com.blow.server.api.service.user;

import com.blow.server.api.dto.user.UserMyPageResponseDTO;

public interface UserService {

    UserMyPageResponseDTO getMyPage(Long userId);
}
