package com.example.spring_auth_server.dao.impl;

import com.example.spring_auth_server.dao.mapper.QuizTypeRowMapper;
import com.example.spring_auth_server.entity.QuizType;
import com.example.spring_auth_server.entity.Submission;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizTypeDAO {
    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//    @Autowired
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    private static final String GET_QUIZ_LIST = "SELECT * FROM quiztype";
//
//    public List<QuizType> getAllQuizType(){
//        return namedParameterJdbcTemplate.query(GET_QUIZ_LIST, new QuizTypeRowMapper());
//    }

    public List<QuizType> getAllQuizType_H(){
        Session session =getCurrentSession();
        Query query = session.createQuery("FROM QuizType ");
        List<QuizType> quizTypeList = query.getResultList();
        return quizTypeList;
    }
}
