package com.example.spring_auth_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {
    @GetMapping("/autherror")
    public String authFail(Model model, HttpServletRequest req){
        model.addAttribute("exception", "Not Allowed");
        model.addAttribute("url", req.getRequestURL());
        return "errorPage";
    }
}
