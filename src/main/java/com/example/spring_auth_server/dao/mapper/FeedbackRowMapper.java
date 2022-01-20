package com.example.spring_auth_server.dao.mapper;

import com.example.spring_auth_server.entity.Feedback;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackRowMapper implements RowMapper {
    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setFid(rs.getInt("fid"));
        feedback.setTid(rs.getInt("tid"));
        feedback.setRating(rs.getInt("rating"));
        feedback.setDescription(rs.getString("description"));
        return feedback;
    }
}
