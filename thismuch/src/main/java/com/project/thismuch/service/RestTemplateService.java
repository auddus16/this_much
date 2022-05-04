package com.project.thismuch.service;

import java.net.URI;
import java.nio.charset.Charset;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.spring.web.json.Json;

@Slf4j
@Service
public class RestTemplateService {
	//Server로 요청을 보내는 서비스이다 .
    //https://developers.kftc.or.kr/proxy/account/transaction_list/fin_num 로 요청을 할 것이다.
    
    
    //1.post방식 요청
    public String searchTran(){
    	
    	RestTemplate restTemplate = new RestTemplate();

    	String url = "https://developers.kftc.or.kr/proxy/account/transaction_list/acnt_num";
    	
    	HttpHeaders httpHeaders = new HttpHeaders();
    	
    	httpHeaders.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDA1NTk0Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2NTk0NDI2NTYsImp0aSI6ImEyYzllNGRhLWRkNjgtNDBlNy1hYTU0LTMzZjQ5NTFlNjNjNSJ9.E3Wi3fMDJNU3ZNiX6PzDNO51mGD1RdqaSPhec7qEgcY");
    	httpHeaders.set("scope", "inquiry login transfer");
    	httpHeaders.set("accept", "application/json");
    	httpHeaders.set("Content-Type", "application/json");
    	
    	MultiValueMap<String, String> body = new LinkedMultiValueMap();
    	
    	body.add("bank_tran_id", "120220057088941031089438");
    	body.add("bank_code_std","004");
    	body.add("account_num", "85120204047322");
    	body.add("user_seq_no", "1101005594");
    	body.add("inquiry_type", "A");
    	body.add("inquiry_base", "T");
    	body.add("from_date", "20200101");
    	body.add("from_time", "100000");
    	body.add("to_date", "20220504");
    	body.add("to_time", "110000");
    	body.add("sort_order", "D");
    	body.add("tran_dtime", "20220504150133");
    	body.add("befor_inquiry_trace_info", "123");
    	
    	HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);
    	
    	System.out.println(requestMessage.getBody());
    	System.out.println(requestMessage.getHeaders());
    	log.info(requestMessage.toString());
    	
    	//URI를 빌드한다 
//        URI uri = UriComponentsBuilder
//                .fromUriString("https://developers.kftc.or.kr/proxy")
//                .path("account/transaction_list/fin_num")
//                .
//                .queryParam("bank_tran_id","120220057088941031089438")
//                .queryParam("fintech_use_num ","M202200570")
//                .queryParam("inquiry_type","A")
//                .queryParam("inquiry_base ","D")
//                .queryParam("from_date ","20220504")
//                .queryParam("to_date ","100000")
//                .queryParam("sort_order","D")
//                .queryParam("tran_dtime ","20220504150133")
//                .encode(Charset.defaultCharset())
//                .build()
//                .toUri();
        
//        log.info(uri.toString());


        //String result = restTemplate.getForObject(uri, String.class);
        //getForEntity는 응답을 ResponseEntity로 받을 수 있도록 해준다 .
        //파라미터 첫번째는 요청 URI 이며 , 2번째는 받을 타입
        HttpEntity<String> response = restTemplate.postForEntity(url, requestMessage, String.class);
        System.out.println(response.getBody());
        
        return response.getBody();
    }
}
