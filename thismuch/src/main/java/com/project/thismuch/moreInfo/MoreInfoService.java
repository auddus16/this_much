package com.project.thismuch.moreInfo;

import com.project.thismuch.data.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MoreInfoService {
    private final MoreInfoRepository moreInfoRepository;

    @Autowired
    public MoreInfoService(MoreInfoRepository moreInfoRepository) {
        this.moreInfoRepository = moreInfoRepository;
    }

    public UserDTO getBasicInfo(Long user_no) {
        return this.moreInfoRepository.findById(user_no).get().toDTO();
    }

    public Long registerNewUser(UserDTO user) {
        return this.moreInfoRepository.saveAndFlush(user.toEntity()).getUserNo();
    }
}
