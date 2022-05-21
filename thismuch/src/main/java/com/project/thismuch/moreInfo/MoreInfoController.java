package com.project.thismuch.moreInfo;

import com.project.thismuch.data.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping(path = "/api/moreInfo")
class MoreInfoController {

	private final MoreInfoService moreInfoService;

	@Autowired
	public MoreInfoController(MoreInfoService moreInfoService) {
		this.moreInfoService = moreInfoService;
	}

	// http://localhost:8080/api/moreInfo/basicInfo
	@Operation(summary = "현재 접속 중인 user의 정보를 조회", description = "user의 정보 조회")
	@GetMapping(path = "/basicInfo")
	public UserDTO getBasicInfo(HttpSession session) {
		Long user_no = (Long) session.getAttribute("user_no");

		log.info(String.valueOf(user_no));

		return this.moreInfoService.getBasicInfo(user_no);
	}

	// http://localhost:8080/api/moreInfo/register
	@PostMapping(path = "/register")
	public void registerNewUser(@RequestBody UserDTO user, HttpSession session) {
		Long user_no = this.moreInfoService.registerNewUser(user);

		log.info(String.valueOf(user_no));
		session.setAttribute("user_no", user_no);
	}
}
