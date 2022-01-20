package com.example.spring_auth_server.dao.mapper;

import com.example.spring_auth_server.entity.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setUserID(resultSet.getInt(1));
        account.setUsername(resultSet.getString("username"));
        account.setPwd(resultSet.getString("password"));

        return account;
    }
}
