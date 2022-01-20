package com.example.spring_auth_server.security.filter;

import com.example.spring_auth_server.constant.JwtConstant;
import com.example.spring_auth_server.security.util.CookieUtil;
import com.example.spring_auth_server.security.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("+++++++++AdminFilter++++++++");
        String role = null;
        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
        if (token!=null) {
            String userName = JwtUtil.getSubjectFromJwt(token);
            System.out.println(userName);
            Claims claims = JwtUtil.getClaimsFromJwt(token);
            if(claims!=null){
                role = claims.get("role").toString();
            }
        }
        if(role!=null && role.equals("admin")){
            filterChain.doFilter(req, res);
        } else {
            String authLoginUrl = getFilterConfig().getInitParameter("authErrorUrl");
            res.sendRedirect(authLoginUrl);
        }
    }

}