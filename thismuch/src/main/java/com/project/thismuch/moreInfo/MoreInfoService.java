package com.project.thismuch.moreInfo;

import com.project.thismuch.data.dao.UserDAO;
import com.project.thismuch.data.dto.UserDTO;
import com.project.thismuch.data.entities.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MoreInfoService implements UserDAO {
    private final MoreInfoRepository moreInfoRepository;

    @Autowired
    public MoreInfoService(MoreInfoRepository moreInfoRepository) {
        this.moreInfoRepository = moreInfoRepository;
    }

    public UserDTO getBasicInfo(Long id) {

        return this.moreInfoRepository.findById(id).get().toDTO();
    }

    public Long registerNewUser(UserDTO user) {
        return this.moreInfoRepository.saveAndFlush(user.toEntity()).getUserNo();
    }
}
