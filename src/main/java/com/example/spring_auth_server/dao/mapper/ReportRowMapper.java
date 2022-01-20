package com.example.spring_auth_server.dao.mapper;

import com.example.spring_auth_server.entity.Report;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportRowMapper implements RowMapper {

    @Override
    public Report mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Report report = new Report();
        report.setRid(resultSet.getInt("rid"));
        report.setSid(resultSet.getInt("sid"));
        report.setQid(resultSet.getInt("qid"));
        report.setAid(resultSet.getInt("aid"));
        report.setCid(resultSet.getInt("cid"));
        return report;
    }
}
