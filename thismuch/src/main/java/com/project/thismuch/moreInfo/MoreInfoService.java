package com.project.thismuch.moreInfo;

import com.project.thismuch.data.dto.UserDTO;
import com.project.thismuch.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MoreInfoService {
    private final UserRepository userRepository;

    @Autowired
    public MoreInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getBasicInfo(Long user_no) {
        return this.userRepository.findById(user_no).get().toDTO();
    }

    public Long registerNewUser(UserDTO user) {
        return this.userRepository.saveAndFlush(user.toEntity()).getUserNo();
    }
}
