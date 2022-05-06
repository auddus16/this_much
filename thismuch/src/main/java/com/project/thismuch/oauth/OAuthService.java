package com.project.thismuch.oauth;

import com.project.thismuch.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class OAuthService {

    private final String base_url = "https://testapi.openbanking.or.kr/oauth/2.0/authorize";
    private final String client_id = "17237a0d-5b2f-4b61-a13d-cbdb79a0923d";
    private final String response_type = "code";
    private final String redirect_uri = "http://localhost:8080/api/oauth/register";
    private final String scope = "login inquiry transfer";
    private final String state = "b80BLsfigm9OokPTjy03elbJqRHOfGSY";
    private final String auth_type = "0";

    private OAuthRepository oauthRepository = null;

    @Autowired
    public OAuthService(OAuthRepository oAuthRepository) {
        this.oauthRepository = oauthRepository;
    }

    public void registerUser(User user) {
        this.oauthRepository.save(user);
    }

    public Map<String, String> saveCodeInfo(String code, String scope, String client_info, String state) {
        Map<String, String> map = new HashMap<>();
        map.put("code",         code);
        map.put("scope",        scope);
        map.put("client_info",   client_info);
        map.put("state",        state);

        return map;
    }

    public String getGrantUrl() {
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

        return url;
    }
}
