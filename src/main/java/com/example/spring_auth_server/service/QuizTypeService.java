package com.example.spring_auth_server.service;

import com.example.spring_auth_server.dao.impl.QuizTypeDAO;
import com.example.spring_auth_server.entity.QuizType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizTypeService {
    @Autowired
    QuizTypeDAO quizTypeDAO;

    public List<QuizType> getAllQuizType(){
        try{
            return quizTypeDAO.getAllQuizType_H();
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
