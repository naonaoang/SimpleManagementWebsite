package com.example.spring_auth_server.dao.impl;

import com.example.spring_auth_server.entity.Feedback;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class FeedbackDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

//    private static final String GET_LIST = "SELECT * FROM quiztype";
//    private static final String GET_COUNT = "SELECT COUNT(fid) FROM feedback";
//    private static final String INSERT_FEEDBACK = "INSERT INTO feedback (fid, tid, rating, description) " +
//            "VALUES (?, ?, ?, ?)";
//
//    public Integer getCount(){
//        return jdbcTemplate.queryForObject(GET_COUNT, Integer.class);
//    }
//
//    public void createFeedback(Feedback feedback){
//        feedback.setFid(getCount()+1);
//        jdbcTemplate.update(INSERT_FEEDBACK, feedback.getFid(), feedback.getTid(), feedback.getRating(), feedback.getDescription());
//    }

    public void createFeedback_H(Feedback feedback){
        Session session = getCurrentSession();
        Serializable id = session.save(feedback);
        System.out.println(id);
    }
}
