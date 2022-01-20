package com.example.spring_auth_server.service;

import com.example.spring_auth_server.dao.impl.ChoiceDAO;
import com.example.spring_auth_server.dao.impl.QuestionDAO;
import com.example.spring_auth_server.entity.Choice;
import com.example.spring_auth_server.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceService {
    @Autowired
    ChoiceDAO choiceDAO;

    public List<Choice> getChoiceByQidList(List<Integer> idList) {
        try {
            return choiceDAO.findChoiceByQuesId_H(idList);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Choice> getChoiceByCidList(List<Integer> idList) {
        try {
            return choiceDAO.findChoiceByChoiceId_H(idList);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Choice> getAnswersForQuestions(List<Integer> idList){
        try{
            return choiceDAO.getAnswersForQuestions_H(idList);
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public Choice getRightChoice(int qid){
        try{
            return choiceDAO.getRightChoice(qid);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void updateChoice(Choice choice){
        try{
            choiceDAO.updateChoice(choice);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public int createChoice(Choice choice){
        try{
            return choiceDAO.createChoice(choice);
        }catch (Exception e){
            System.out.println(e);
        }
        return -1;
    }
}
