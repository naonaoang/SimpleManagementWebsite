package com.example.spring_auth_server.service;

import com.example.spring_auth_server.dao.impl.QuestionDAO;
import com.example.spring_auth_server.entity.Question;
import com.example.spring_auth_server.entity.Submission;
import com.example.spring_auth_server.exception.AccountCreateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;

    public List<Question> getQuestionById(List<Integer> idList){
        try{
            return questionDAO.findQuestionById_H(idList);
        }catch (Exception e){
            System.out.println("Wrong");
        }
        return null;
    }

    public List<Question> getRandomQuestion(int tid){
        try{
            return questionDAO.getRandomQuestion_H(tid);
        }catch (Exception e){
            System.out.println("Wrong");
        }
        return null;
    }

    public int createQuestion(Question question) {
        try {
            return questionDAO.createQuestion(question);
        } catch (Exception e) {
            throw new AccountCreateException("");
        }
    }

    public List<Question> getQuestionByType(int tid){
        try{
            return questionDAO.getQuestionByType_H(tid);
        }catch (Exception e){
            System.out.println("Wrong");
        }
        return null;
    }

    public void updateQuestion(Question question){
        try{
            questionDAO.updateQuestion(question);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void updateStatus(int qid, String status){
        try{
            questionDAO.updateQuesStatus_H(qid, status);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
