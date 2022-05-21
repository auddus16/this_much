package com.project.thismuch.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<Map<String, String>> register(
            @RequestParam String code,
            @RequestParam String scope,
            @RequestParam String state,
            HttpSession session
    ) {
        Long user_no = (Long) session.getAttribute("user_no");
        log.info(String.format("api/oauth/register: user_no: %d", user_no));

        this.oauthService.updateCode(user_no, code);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                body(this.oauthService.saveCodeInfo(code, scope, state));
    }

    @GetMapping(path = "/grant")
    public RedirectView grant() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(this.oauthService.getGrantUrl());
        return redirectView;
    }
}
