package com.project.thismuch.moreInfo;

import com.project.thismuch.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class MoreInfoService {

    private MoreInfoRepository moreInfoRepository;

    @Autowired
    public MoreInfoService(MoreInfoRepository moreInfoRepository) {
        this.moreInfoRepository = moreInfoRepository;
    }

    public Optional<User> getBasicInfo() {
        Long id = 1L;

        return this.moreInfoRepository.findById(id);
    }

    public void registerNewUser(User user) {
        this.moreInfoRepository.save(user);
    }
}
