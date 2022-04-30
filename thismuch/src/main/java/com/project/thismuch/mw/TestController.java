package com.project.thismuch.mw;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mw")
public class TestController {
	@GetMapping("/test")
	public ResponseEntity<Map<String, Object>> hi() {
		Map<String, Object> map = new HashMap<>();
		map.put("text", "hello");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}
}