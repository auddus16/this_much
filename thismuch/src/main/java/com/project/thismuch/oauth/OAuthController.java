package com.project.thismuch.oauth;

import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.utils.URLPathEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "api/oauth/")
public class OAuthController {

    private final String base_url = "https://testapi.openbanking.or.kr/oauth/2.0/authorize";
    private final String client_id = "17237a0d-5b2f-4b61-a13d-cbdb79a0923d";
    private final String response_type = "code";
    private final String redirect_uri = "http://localhost:8080/api/oauth/register/";
    private final String scope = "login inquiry transfer";
    private final String state = "b80BLsfigm9OokPTjy03elbJqRHOfGSY";
    private final String auth_type = "0";

    @GetMapping(path = "register/")
    public ResponseEntity<Map<String, String>> register(
            @RequestParam String code, @RequestParam String scope,
            @RequestParam String client_info, @RequestParam String state
    ) {
        log.info("test연결 완료");
        Map<String, String> map = new HashMap<>();
        map.put("code",         code);
        map.put("scope",        scope);
        map.put("client_info",   client_info);
        map.put("state",        state);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
    }

    @GetMapping(path = "grant/")
    public ResponseEntity<String> grant() {
        String url = "";

        url += base_url;
        url += "?";
        try {
            url += String.format("response_type=%s&", URLEncoder.encode(response_type, "UTF-8"));
            url += String.format("client_id=%s&", URLEncoder.encode(client_id, "UTF-8"));
            url += String.format("redirect_uri=%s&", URLEncoder.encode(redirect_uri, "UTF-8"));
            url += String.format("scope=%s&", URLEncoder.encode(scope, "UTF-8"));
            url += String.format("state=%s&", URLEncoder.encode(state, "UTF-8"));
            url += String.format("auth_type=%s", URLEncoder.encode(auth_type, "UTF-8"));

            // URLEncode.encode() encode "space" into "+". So substitude '+' to "%20"
            url = url.replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(url);
    }
}
