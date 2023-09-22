package com.blow.server.api.service.home;

import com.blow.server.api.dto.home.response.HomeResponseDTO;
import com.blow.server.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HomeServiceImpl implements HomeService{
    private final UserRepository userRepository;

    @Override
    public HomeResponseDTO getHome(Long userId){
        val user = userRepository.findById(userId);
        return HomeResponseDTO.of(user.get());
    }
}
