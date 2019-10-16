package com.main.SimpleWeb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController("/userservice")
public class EventController {
    @PostMapping("/add")
    public String newOrder(@RequestBody String orderRequest) {
        String eventUrl = "http://event-api-cluster-ip-service:8081/userservice/add/";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(eventUrl, orderRequest, String.class);
        return responseEntity.getBody() + " : " + responseEntity.getStatusCode();
    }
}
