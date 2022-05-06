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

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/oauth")
public class OAuthController {

    private OAuthService oauthService;

    @Autowired
    public OAuthController(OAuthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping(path = "/register")
    public ResponseEntity<Map<String, String>> register(
            @RequestParam String code, @RequestParam String scope,
            @RequestParam String client_info, @RequestParam String state
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                body(this.oauthService.saveCodeInfo(code, scope, client_info, state));
    }

    @GetMapping(path = "/grant")
    public RedirectView exRedirect4() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(this.oauthService.getGrantUrl());
        return redirectView;
    }
}
