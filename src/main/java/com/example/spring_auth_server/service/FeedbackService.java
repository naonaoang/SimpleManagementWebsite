package com.example.spring_auth_server.service;

import com.example.spring_auth_server.dao.impl.FeedbackDAO;
import com.example.spring_auth_server.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    @Autowired
    FeedbackDAO feedbackDAO;
    public void createFeedback(Feedback feedback){
        try{
            feedbackDAO.createFeedback_H(feedback);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
