package com.blow.server.api.service.home;

import com.blow.server.api.dto.home.response.HomeResponseDTO;

public interface HomeService {

    HomeResponseDTO getHome(Long userId);
}
