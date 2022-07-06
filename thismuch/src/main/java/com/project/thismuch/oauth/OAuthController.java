package com.project.thismuch.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
@RestController
@RequestMapping(value = "/api/oauth")
public class OAuthController {

    private final OAuthService oauthService;

    @Autowired
    public OAuthController(OAuthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping(path = "/register")
    public ResponseEntity<?> register(
            @RequestParam String code,
            @RequestParam String scope,
            @RequestParam String state,
            HttpSession session
    ) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        log.info(code);
        params.add("code", code);
        params.add("client_id", "17237a0d-5b2f-4b61-a13d-cbdb79a0923d");
        params.add("client_secret", "f8ef71b4-f50b-4183-b7a4-a577e0659ada");
        params.add("redirect_uri", "http://localhost:8080/api/oauth/register");
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
        log.info(entity.toString());

        RestTemplate rt = new RestTemplate();

        return rt.exchange(
                "https://testapi.openbanking.or.kr/oauth/2.0/token",
                HttpMethod.POST,
                entity,
                String.class
        );
    }

    @GetMapping(path = "/grant")
    public RedirectView grant() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(this.oauthService.getGrantUrl());
        return redirectView;
    }
}
