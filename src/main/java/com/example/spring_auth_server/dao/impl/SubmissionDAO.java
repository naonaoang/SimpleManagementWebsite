package com.example.spring_auth_server.dao.impl;

import com.example.spring_auth_server.dao.mapper.AccountRowMapper;
import com.example.spring_auth_server.dao.mapper.SubmissionRowMapper;
import com.example.spring_auth_server.entity.Account;
import com.example.spring_auth_server.entity.Submission;
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
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SubmissionDAO {
    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//    private static final String GET_SUBMISSION_BY_ID = "SELECT * FROM submission WHERE userID=:userID";
//    private static final String INSERT_SUBMISSION = "INSERT INTO submission (sid, userid, typeID, pass) " +
//            "VALUES (?, ?, ?, ?)";
//    private static final String GET_SUBMISSION_LIST = "SELECT * FROM submission";
    private static final String GET_COUNT = "SELECT COUNT(sid) FROM submission";

    public Integer getCount(){
        return jdbcTemplate.queryForObject(GET_COUNT, Integer.class);
    }

//    public List<Submission> getSubmissionList() {
//        List<Submission> submissionList = namedParameterJdbcTemplate.query(GET_SUBMISSION_LIST, new SubmissionRowMapper());
//        return submissionList;
//    }
//
//    public List<Submission> getSubmissionById(int userID) {
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("userID", userID);
//        return namedParameterJdbcTemplate.query(GET_SUBMISSION_BY_ID, parameterSource, new SubmissionRowMapper());
//    }
//
//    public Submission createSubmission(Submission submission) {
//        submission.setSID(getCount() + 1);
//        jdbcTemplate.update(INSERT_SUBMISSION, submission.getSID(), submission.getUserID(), submission.getTypeID(), submission.isPass());
//        return submission;
//    }

    public List<Submission> getSubmissionList_H() {
        Session session =getCurrentSession();
        Query query = session.createQuery("FROM Submission ");
        List<Submission> submissionList = query.getResultList();
        return submissionList;
    }

    public List<Submission> getSubmissionByUserId_H(int userID) {
        Session session =getCurrentSession();
        Query query = session.createQuery("FROM Submission s WHERE s.userID = :userID");
        query.setParameter("userID", userID);
        List<Submission> submissionList = query.getResultList();
        return submissionList;
    }

    public Submission getSubmissionBySubId_H(int sid) {
        Session session =getCurrentSession();
        Query query = session.createQuery("FROM Submission s WHERE s.sID = :sid");
        query.setParameter("sid", sid);
        Submission submission = (Submission) query.getSingleResult();
        return submission;
    }

    public List<Submission> getSubmissionByTypeId_H(int tid) {
        Session session =getCurrentSession();
        Query query = session.createQuery("FROM Submission s WHERE s.typeID = :typeID");
        query.setParameter("typeID", tid);
        List<Submission> submissionList = query.getResultList();
        return submissionList;
    }

    public void createSubmission_H(Submission submission) {
        Session session = getCurrentSession();
        Serializable id = session.save(submission);
        System.out.println(id);
    }
}
