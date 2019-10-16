package com.main.SimpleWeb.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/userservice")
public class EventController {
    @PostMapping("/add")
    public String newOrder(@RequestBody String orderRequest) {
        String eventUrl = "http://event-api-cluster-ip-service:8081/userservice/add/";
        String url = "http://10.0.60.234:8086/add/order/details/";
        System.out.println("request : "+orderRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(orderRequest, headers);
        
        
        
        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, orderRequest, String.class);
        ResponseEntity<String> responseEntity = restTemplate.exchange(eventUrl, HttpMethod.POST, request, new ParameterizedTypeReference<String>() { });
        return responseEntity.getBody() + " : " + responseEntity.getStatusCode();
    }
    @GetMapping("/test")
    public String test() {
    	return "test";
    }
}
