package com.example.spring_auth_server.dao.mapper;

import com.example.spring_auth_server.entity.QuizType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizTypeRowMapper implements RowMapper {
    @Override
    public QuizType mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuizType quizType = new QuizType();
        quizType.setTid(rs.getInt("tid"));
        quizType.setDescription(rs.getString("description"));
        return quizType;
    }
}
