package com.project.thismuch.moreInfo;

import com.project.thismuch.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoreInfoRepository extends JpaRepository<User, Long> {
}
