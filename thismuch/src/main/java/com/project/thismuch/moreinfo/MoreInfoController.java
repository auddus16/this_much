package com.project.thismuch.moreinfo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "more_info/")
class MoreInfoController {

	private final MoreInfoService moreInfoService;

	@Autowired
	public MoreInfoController(MoreInfoService moreInfoService) {
		this.moreInfoService = moreInfoService;
	}

	@GetMapping
	public List<String> hi() {
		return this.moreInfoService.getInfoLists();
	}
}
