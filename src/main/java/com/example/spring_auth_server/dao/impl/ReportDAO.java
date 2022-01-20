package com.example.spring_auth_server.dao.impl;

import com.example.spring_auth_server.dao.mapper.AccountRowMapper;
import com.example.spring_auth_server.dao.mapper.ReportRowMapper;
import com.example.spring_auth_server.entity.Question;
import com.example.spring_auth_server.entity.Report;
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
import java.util.List;

@Repository
public class ReportDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

//    private static final String GET_REPORT_BY_SID = "SELECT * FROM report WHERE sid= :sid";
//    private static final String GET_COUNT = "SELECT COUNT(rid) FROM report";
//    private static final String INSERT_REPORT = "INSERT INTO report (rid, sid, qid, aid, cid) " +
//            "VALUES (?, ?, ?, ?, ?)";
//
//    public Integer getCount(){
//        return jdbcTemplate.queryForObject(GET_COUNT, Integer.class);
//    }
//
//    public List<Report> getReportBySubId(int sid){
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("sid", sid);
//        return namedParameterJdbcTemplate.query(GET_REPORT_BY_SID, parameterSource, new ReportRowMapper());
//    }
//
//    public void createReport(Report report){
//        report.setRid(getCount() + 1);
//        jdbcTemplate.update(INSERT_REPORT, report.getRid(), report.getSid(), report.getQid(), report.getAid(), report.getCid());
//    }

    public List<Report> getReportBySubId_H(int sid){
        Session session =getCurrentSession();
        Query query = session.createQuery("FROM Report r WHERE r.sid = :sid");
        query.setParameter("sid", sid);
        List<Report> reportList = query.getResultList();
        return reportList;
    }

    public void createReport_H(Report report){
        Session session = getCurrentSession();
        Serializable id = session.save(report);
        System.out.println(id);
    }
}
