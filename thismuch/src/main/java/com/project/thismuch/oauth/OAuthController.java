package com.project.thismuch.oauth;

import io.swagger.v3.oas.models.links.Link;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Map;

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
//        Long user_no = (Long) session.getAttribute("user_no");
//        log.info(String.format("api/oauth/register: user_no: %d", user_no));

//        this.oauthService.updateCode(user_no, code);

        return getAccessToken(code, scope, state);
    }

    @PostMapping(path = "/redirectAccessToken")
    public ResponseEntity<?> redirectAccessToken(
            @RequestBody String access_token,
            @RequestBody String token_type,
            @RequestBody String expires_in,
            @RequestBody String refresh_token,
            @RequestBody String scope,
            @RequestBody String user_seq_no) {

        log.info(access_token);
        log.info(token_type);
        log.info(expires_in);
        log.info(refresh_token);
        log.info(scope);
        log.info(user_seq_no);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    public ResponseEntity<?> getAccessToken(String code, String scope, String state) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", scope);
        params.add("client_secret", state);
        params.add("redirect_uri", "");
        params.add("grant_type", "0");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> response = rt.exchange(
                "https://testapi.openbanking.or.kr/oauth/2.0/token",
                HttpMethod.POST,
                entity,
                String.class
        );

        log.info(response.toString());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping(path = "/grant")
    public RedirectView grant() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(this.oauthService.getGrantUrl());
        return redirectView;
    }
}
