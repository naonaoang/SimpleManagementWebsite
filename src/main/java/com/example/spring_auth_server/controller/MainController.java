package com.example.spring_auth_server.controller;


import com.example.spring_auth_server.constant.JwtConstant;
import com.example.spring_auth_server.domain.AccountDomain;
import com.example.spring_auth_server.entity.Account;
import com.example.spring_auth_server.exception.AccountCreateException;
import com.example.spring_auth_server.security.util.CookieUtil;
import com.example.spring_auth_server.security.util.JwtUtil;
import com.example.spring_auth_server.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes({"username","password"})
public class MainController {

    @Autowired
    public AccountService accountService;

    @PostMapping("/login")
    public String login(Model model, HttpServletRequest req, HttpServletResponse res,
                        @RequestParam String username, @RequestParam String pwd){
        int uid = accountService.checkLogin(username,pwd);
        if (uid != -1) {
            Account user = accountService.getAccountById(uid);
            if(user.getUsergroup().equals("ban")){
                model.addAttribute("exception", "You are banned");
                model.addAttribute("url", req.getRequestURL());
                return "errorPage";
            }
            HttpSession oldSession = req.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            HttpSession newSession = req.getSession(true);//-> session id -> cookie -> return back to client
            model.addAttribute("username", username);
            model.addAttribute("password", pwd);
            String jwt = JwtUtil.generateToken(username, JwtConstant.JWT_VALID_DURATION, user.getUsergroup(), user.getUserID());
            CookieUtil.create(res, JwtConstant.JWT_COOKIE_NAME, jwt, false, -1, "localhost");
            if(user.getUsergroup().equals("admin")){
                return "redirect:/admin/mainpage?adminid="+uid;
            }
            else {
                return "redirect:/quiz/profile?uid=" + uid;
            }
        } else {
            model.addAttribute("exception", "Wrong UserName or Password");
            model.addAttribute("url", req.getRequestURL());
            return "errorPage";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, SessionStatus sessionStatus, HttpServletResponse res){
        sessionStatus.setComplete();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        CookieUtil.clear(res, JwtConstant.JWT_COOKIE_NAME, "localhost");
        return "index";
    }

    @GetMapping("/reg")
    public String saveAccount(Model model){
        model.addAttribute("account",new AccountDomain());
        return "register";
    }

    @PostMapping("/create")
    public String createAccount(HttpServletRequest req,Model model,@ModelAttribute("account") @Valid AccountDomain accountDomain,
                               BindingResult result) {

        if (result.hasErrors()) {
            return "register";
        }
        try {
            accountService.createAccount(accountDomain);
            return "redirect:" + "/";
        } catch (AccountCreateException e) {
            model.addAttribute("exception", e.getMessage());
            model.addAttribute("url", req.getRequestURL());
            return "errorPage";
        }
    }

    @GetMapping("/contact")
    public String contact(){
        return "/contact";
    }

}
