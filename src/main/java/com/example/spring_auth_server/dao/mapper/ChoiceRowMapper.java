package com.example.spring_auth_server.dao.mapper;

import com.example.spring_auth_server.entity.Choice;
import com.example.spring_auth_server.entity.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class ChoiceRowMapper implements RowMapper<Choice>{
    @Override
    public Choice mapRow(ResultSet resultSet, int i) throws SQLException {
        Choice choice = new Choice();
        choice.setQid(resultSet.getInt("qid"));
        choice.setCid(resultSet.getInt("cid"));
        choice.setAnswer(resultSet.getBoolean("isAnswer"));
        choice.setDescription(resultSet.getString("description"));
        return choice;
    }
}
