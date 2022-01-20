package com.example.spring_auth_server.dao.impl;

import com.example.spring_auth_server.dao.mapper.ChoiceRowMapper;
import com.example.spring_auth_server.dao.mapper.QuestionRowMapper;
import com.example.spring_auth_server.entity.Choice;
import com.example.spring_auth_server.entity.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ChoiceDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

//    private static final String GET_QUESTION_BY_ID = "SELECT * FROM question WHERE qid=?";
//    private static final String GET_CHOICES_IN_QIDLIST = "SELECT * FROM choice where qid IN (:idList)";
//    private static final String GET_CHOICES_IN_CIDLIST = "SELECT * FROM choice where cid IN (:idList)";
//    private static final String GET_ANSWERS_FOR_QUESTIONS = "select * from choice where isAnswer = true and qid in (:idList)";
//
//    public List<Choice> findChoiceByQuesId(List<Integer> idList){
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("idList", idList);
//        List<Choice> choiceList = namedParameterJdbcTemplate.query(GET_CHOICES_IN_QIDLIST, parameterSource, new ChoiceRowMapper());
//        return choiceList;
//    }
//    public List<Choice> findChoiceByChoiceId(List<Integer> idList){
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("idList", idList);
//        List<Choice> choiceList = namedParameterJdbcTemplate.query(GET_CHOICES_IN_CIDLIST, parameterSource, new ChoiceRowMapper());
//        return choiceList;
//    }
//
//    public List<Choice> getAnswersForQuestions(List<Integer> idList){
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("idList", idList);
//        List<Choice> choiceList = namedParameterJdbcTemplate.query(GET_ANSWERS_FOR_QUESTIONS, parameterSource, new ChoiceRowMapper());
//        return choiceList;
//    }

    public List<Choice> findChoiceByQuesId_H(List<Integer> idList){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Choice c WHERE c.qid IN (:idList)");
        query.setParameter("idList", idList);
        List<Choice> choiceList = query.getResultList();
        return choiceList;
    }

    public List<Choice> findChoiceByChoiceId_H(List<Integer> idList){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Choice c WHERE c.cid IN (:idList)");
        query.setParameter("idList", idList);
        List<Choice> choiceList = query.getResultList();
        return choiceList;
    }

    public List<Choice> getAnswersForQuestions_H(List<Integer> idList){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Choice c WHERE c.qid IN (:idList) AND c.answer = true");
        query.setParameter("idList", idList);
        List<Choice> choiceList = query.getResultList();
        return choiceList;
    }

    public int createChoice(Choice choice){
        Session session = getCurrentSession();
        Serializable id = session.save(choice);
        System.out.println(id);
        return (int)id;
    }

    public Choice getRightChoice(int qid){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Choice c WHERE c.qid = :qid AND c.answer = true");
        query.setParameter("qid", qid);
        Choice choice = (Choice) query.getSingleResult();
        return choice;
    }

    public void updateChoice(Choice update){
        Session session = getCurrentSession();
        session.beginTransaction();
        Choice choice = findChoiceByChoiceId_H(Arrays.asList(update.getCid())).get(0);
        choice.setQid(update.getQid());
        choice.setAnswer(update.isAnswer());
        choice.setDescription(update.getDescription());
        session.update(choice);
        session.getTransaction().commit();
    }
}
