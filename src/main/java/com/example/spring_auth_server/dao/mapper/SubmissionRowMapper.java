package com.example.spring_auth_server.dao.mapper;

import com.example.spring_auth_server.entity.Submission;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubmissionRowMapper implements RowMapper<Submission> {
    @Override
    public Submission mapRow(ResultSet resultSet, int i) throws SQLException {
        Submission submission = new Submission();
        submission.setSID(resultSet.getInt("sid"));
        submission.setUserID(resultSet.getInt("userid"));
        submission.setTypeID(resultSet.getInt("typeid"));
        submission.setPass(resultSet.getBoolean("pass"));
        return submission;
    }
}
