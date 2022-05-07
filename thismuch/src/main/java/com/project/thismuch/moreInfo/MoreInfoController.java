package com.project.thismuch.moreInfo;

import com.project.thismuch.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/moreInfo")
class MoreInfoController {

	private final MoreInfoService moreInfoService;

	@Autowired
	public MoreInfoController(MoreInfoService moreInfoService) {
		this.moreInfoService = moreInfoService;
	}

	@GetMapping(path = "/basicInfo")
	public Optional<User> getBasicInfo() {
		return this.moreInfoService.getBasicInfo();
	}

	@GetMapping(path = "/authSecure")
	public Optional<User> getAuthInfo() {
		return this.moreInfoService.getBasicInfo();
	}

	@PostMapping(path = "/register")
	public void registerNewUser(@RequestBody User user) {
		this.moreInfoService.registerNewUser(user);
	}
}
