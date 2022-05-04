package com.project.thismuch.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "api/oauth/")
public class OAuthController {

    private final String client_id = "17237a0d-5b2f-4b61-a13d-cbdb79a0923d";

    @GetMapping(path = "register/")
    public ResponseEntity<Map<String, String>> register(
            @RequestParam String code,
            @RequestParam String scope,
            @RequestParam String client_info,
            @RequestParam String state
    ) {
        log.info("test연결 완료");
        Map<String, String> map = new HashMap<>();
        map.put("code",         code);
        map.put("scope",        scope);
        map.put("clien_info",   client_info);
        map.put("state",        state);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
    }
}
