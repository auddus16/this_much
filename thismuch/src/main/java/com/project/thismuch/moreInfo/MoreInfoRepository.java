package com.project.thismuch.moreInfo;

import com.project.thismuch.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoreInfoRepository extends JpaRepository<UserEntity, Long> {
}
