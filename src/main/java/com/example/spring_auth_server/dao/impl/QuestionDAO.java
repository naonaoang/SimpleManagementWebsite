package com.example.spring_auth_server.dao.impl;

import com.example.spring_auth_server.dao.mapper.QuestionRowMapper;
import com.example.spring_auth_server.entity.Account;
import com.example.spring_auth_server.entity.Question;
import com.example.spring_auth_server.entity.Submission;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class QuestionDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

//    private static final String GET_QUESTION_BY_TID = "SELECT * FROM question WHERE tid =:tid";
//    private static final String GET_QUESTIONS_IN_IDLIST = "SELECT * FROM question where qid IN (:idList)";
//
//    public List<Question> findQuestionById(List<Integer> idList){
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("idList", idList);
//        List<Question> questionList = namedParameterJdbcTemplate.query(GET_QUESTIONS_IN_IDLIST, parameterSource, new QuestionRowMapper());
//        return questionList;
//    }
//
//    public List<Question> getQuestionByType(int tid){
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("tid", tid);
//        List<Question> questionList = namedParameterJdbcTemplate.query(GET_QUESTION_BY_TID, parameterSource, new QuestionRowMapper());
//        return questionList;
//    }
//
//    public List<Question> getRandomQuestion(int tid){
//            List<Question> questionList = getQuestionByType(tid);
//            Collections.shuffle(questionList);
//            return questionList.subList(0,10);
//    }

    public List<Question> findQuestionById_H(List<Integer> idList){
        Session session =getCurrentSession();
        Query query = session.createQuery("FROM Question q WHERE q.qid IN (:idList)");
        query.setParameter("idList", idList);
        List<Question> questionList = query.getResultList();
        return questionList;
    }

    public List<Question> getQuestionByType_H(int tid){
        Session session =getCurrentSession();
        Query query = session.createQuery("FROM Question q WHERE q.tid = :tid");
        query.setParameter("tid", tid);
        List<Question> questionList = query.getResultList();
        return questionList;
    }

    public List<Question> getActiveQuestionByType_H(int tid){
        Session session =getCurrentSession();
        Query query = session.createQuery("FROM Question q WHERE q.tid = :tid AND q.status = :status");
        query.setParameter("tid", tid);
        query.setParameter("status", "active");
        List<Question> questionList = query.getResultList();
        return questionList;
    }

    public List<Question> getRandomQuestion_H(int tid){
        List<Question> questionList = getActiveQuestionByType_H(tid);
        Collections.shuffle(questionList);
        return questionList.subList(0,10);
    }

    public Question getSingleQuestionById_H(int qid){
        Session session =getCurrentSession();
        Query query = session.createQuery("FROM Question q WHERE q.qid = :qid");
        query.setParameter("qid", qid);
        Question question = (Question) query.getSingleResult();
        return question;
    }

    public int createQuestion(Question question){
        Session session = getCurrentSession();
        Serializable id = session.save(question);
        System.out.println(id);
        return (int)id;
    }

    public void updateQuestion(Question update){
        Session session = getCurrentSession();
        session.beginTransaction();
        Question question = getSingleQuestionById_H(update.getQid());
        question.setTid(update.getTid());
        question.setDescription(update.getDescription());
        session.update(question);
        session.getTransaction().commit();
    }

    public void updateQuesStatus_H(int qid, String status){
        Session session = getCurrentSession();
        session.beginTransaction();
        Question question = getSingleQuestionById_H(qid);
        question.setStatus(status);
        session.update(question);
        session.getTransaction().commit();
    }
}
