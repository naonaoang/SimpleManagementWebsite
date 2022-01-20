package com.example.spring_auth_server.controller;

import com.example.spring_auth_server.entity.*;
import com.example.spring_auth_server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    public SubmissionService submissionService;
    @Autowired
    public QuizTypeService quizTypeService;
    @Autowired
    public AccountService accountService;
    @Autowired
    public ReportService reportService;
    @Autowired
    QuestionService questionService;
    @Autowired
    ChoiceService choiceService;

    @GetMapping({"/quiz/profile?uid=","/quiz/profile"})
    public String profile(HttpServletRequest req,Model model, @RequestParam(name = "uid") Integer uid){
        List<QuizType> quizTypeList = quizTypeService.getAllQuizType();
        List<Submission> list = submissionService.getSubmissionByID(uid);
        model.addAttribute("quiztypes", quizTypeList);
        model.addAttribute("submissions",list);
        model.addAttribute("uid",uid);
        return "/quiz/profile";

    }

    @GetMapping("/quiz/contactus")
    public String contactUs(HttpServletRequest req,Model model, @RequestParam(name = "uid") Integer uid){
        model.addAttribute("uid", uid);
        return "/quiz/contactus";
    }

}
