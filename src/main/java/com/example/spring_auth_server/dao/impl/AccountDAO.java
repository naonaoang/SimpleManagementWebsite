package com.example.spring_auth_server.dao.impl;



import com.example.spring_auth_server.dao.mapper.AccountRowMapper;
import com.example.spring_auth_server.domain.AccountDomain;
import com.example.spring_auth_server.entity.Account;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.io.Serializable;
import java.util.stream.Collectors;

@Repository
public class AccountDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    private static final String GET_ACCOUNT_BY_NAME = "SELECT * FROM useraccount WHERE username= :username";
    private static final String GET_ACCOUNT_BY_ID = "SELECT * FROM useraccount WHERE userID= :userID";
    private static final String INSERT_ACCOUNT = "INSERT INTO useraccount (userID, username, password) " +
            "VALUES (?, ?, ?)";
    private static final String GET_ACCOUNT_LIST = "SELECT * FROM useraccount";
    private static final String GET_COUNT = "SELECT COUNT(userID) FROM useraccount";

    public Integer getCount(){
        return jdbcTemplate.queryForObject(GET_COUNT, Integer.class);
    }

//    public List<Account> findAccountByName(String username){
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("username", username);
//        return namedParameterJdbcTemplate.query(GET_ACCOUNT_BY_NAME, parameterSource, new AccountRowMapper());
//    }
//
//    public List<Account> getAccountById(int userID) {
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("userID", userID);
//        return namedParameterJdbcTemplate.query(GET_ACCOUNT_BY_ID, parameterSource, new AccountRowMapper());
//    }
//
//    public List<Account> getAccountList() {
//        List<Account> accountList = namedParameterJdbcTemplate.query(GET_ACCOUNT_LIST, new AccountRowMapper());
//        return accountList;
//    }
//
//    public Account createAccount(Account account) {
//        account.setUserID(getCount() + 1);
//        jdbcTemplate.update(INSERT_ACCOUNT, account.getUserID(), account.getUsername(), account.getPwd());
//        return account;
//    }

    public List<Account> findAccountByName_H(String username){
        Session session =getCurrentSession();
        Query query = session.createQuery("FROM Account a WHERE a.username = :username");
        query.setParameter("username", username);
        List<Account> accounts = query.getResultList();
        return accounts;
    }

    public Account getAccountById_H(int userID) {
        Session session =getCurrentSession();
        Query query = session.createQuery("FROM Account a WHERE a.userID = :userID");
        query.setParameter("userID", userID);
        Account account = (Account) query.getSingleResult();
        return account;
    }

    public void createAccount_H(AccountDomain accountDomain) {
        Session session = getCurrentSession();
        Account newAccount = new Account();
        newAccount.setUsername(accountDomain.getUsername());
        newAccount.setPwd(accountDomain.getPwd());
        newAccount.setUsergroup("user");
        Serializable id = session.save(newAccount);
        System.out.println(id);
    }

    public List<Account> getAccountList_H() {
        Session session =getCurrentSession();
        Query query = session.createQuery("FROM Account");
        List<Account> accounts = query.getResultList();
        return accounts;
    }

    public void updateUserGroup_H(int uid, String group){
        Session session = getCurrentSession();
        session.beginTransaction();
        Account account = getAccountById_H(uid);
        account.setUsergroup(group);
        session.update(account);
        session.getTransaction().commit();
    }
}
