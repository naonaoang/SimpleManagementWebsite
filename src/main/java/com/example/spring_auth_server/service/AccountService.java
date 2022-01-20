package com.example.spring_auth_server.service;

import java.util.*;
import com.example.spring_auth_server.dao.impl.AccountDAO;
import com.example.spring_auth_server.domain.AccountDomain;
import com.example.spring_auth_server.entity.Account;
import com.example.spring_auth_server.entity.Question;
import com.example.spring_auth_server.entity.Submission;
import com.example.spring_auth_server.exception.AccountCreateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    public AccountDAO accountDAO;

    public int checkLogin(String username, String password){
        if(username == null || password == null)
            return -1;
        List<Account> res = accountDAO.findAccountByName_H(username);
        if(res.size() == 0)
            return -1;
        for(int i = 0; i<res.size(); i++){
            if(res.get(i).pwd.equals(password))
                return res.get(i).userID;
        }
        return -1;
    }

    public void createAccount(AccountDomain accountDomain) {

        try {
            accountDAO.createAccount_H(accountDomain);

        } catch (Exception e) {
            throw new AccountCreateException(accountDomain.toString());
        }
    }

    public Account getAccountById(int uid){
        try{
            return accountDAO.getAccountById_H(uid);
        }catch (Exception e){
            System.out.println("Wrong");
        }
        return null;
    }

    public List<Account> getAllAccount(){
        try{
            return accountDAO.getAccountList_H();
        }
        catch (Exception e){
            System.out.println("Wrong");
        }
        return null;
    }

    public void updateGroup(int uid, String group){
        try{
            accountDAO.updateUserGroup_H(uid, group);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
