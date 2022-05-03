package com.project.thismuch.moreinfo;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MoreInfoRepository {

    private final List<String> infoLists;

    public MoreInfoRepository() {
        this.infoLists = List.of(
                "기본 정보",
                "인증 및 보안",
                "지출 카테고리 및 상한 금액 설정",
                "마이페이지 수정"
        );
    }

    public List<String> getInfoLists() {
        return this.infoLists;
    }
}
