package com.example.spring_auth_server.dao.mapper;

import com.example.spring_auth_server.entity.Question;
import com.example.spring_auth_server.entity.Submission;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper {

    @Override
    public Question mapRow(ResultSet resultSet, int i) throws SQLException {
        Question question = new Question();
        question.setQid(resultSet.getInt("qid"));
        question.setTid(resultSet.getInt("tid"));
        question.setDescription(resultSet.getString("description"));
        return question;
    }
}
