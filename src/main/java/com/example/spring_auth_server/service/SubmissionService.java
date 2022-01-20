package com.example.spring_auth_server.service;

import com.example.spring_auth_server.dao.impl.SubmissionDAO;
import com.example.spring_auth_server.entity.Submission;
import com.example.spring_auth_server.exception.AccountCreateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {
    @Autowired
    SubmissionDAO submissionDAO;

    public void createSubmission(Submission submission) {
        try {
            submissionDAO.createSubmission_H(submission);
        } catch (Exception e) {
            throw new AccountCreateException("");

        }
    }

    public List<Submission> getSubmissionByID(int userID){
        try{
            return submissionDAO.getSubmissionByUserId_H(userID);
        }catch (Exception e){
            System.out.println("Wrong");
        }
        return null;
    }

    public List<Submission> getSubmissionByType(int tid){
        try{
            return submissionDAO.getSubmissionByTypeId_H(tid);
        }catch (Exception e){
            System.out.println("Wrong");
        }
        return null;
    }

    public int getNewId(){
        return submissionDAO.getCount()+1;
    }

    public List<Submission> getAllSubmission(){
        try{
            return submissionDAO.getSubmissionList_H();
        }
        catch (Exception e){
            System.out.println("Wrong");
        }
        return null;
    }

}
