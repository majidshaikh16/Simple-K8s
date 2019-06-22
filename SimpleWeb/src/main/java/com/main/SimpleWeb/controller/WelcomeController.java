/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.SimpleWeb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author webwerks
 */
@RestController
@RequestMapping("/home")
public class WelcomeController {
    @GetMapping
    public String greet(){
        return "welcome to devops learn and earn!";
    }
}
