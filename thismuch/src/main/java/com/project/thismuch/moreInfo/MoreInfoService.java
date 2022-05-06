package com.project.thismuch.more_info;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MoreInfoService {

    private MoreInfoRepository moreInfoRepository;

    @Autowired
    public MoreInfoService(MoreInfoRepository moreInfoRepository) {
        this.moreInfoRepository = moreInfoRepository;
    }

    public List<String> getInfoLists() {
        return this.moreInfoRepository.getInfoLists();
    }
}
