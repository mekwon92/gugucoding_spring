package com.me92100984.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getAllData() {
        String sql = "SELECT name FROM test_table";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("name"));
        
    }
}