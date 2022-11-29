package com.johnson.spring.repository;

import com.johnson.spring.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HolidayRepository {

    private final BeanPropertyRowMapper<Holiday> holidayRowMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public HolidayRepository() {
        this.holidayRowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);

    }

    public List<Holiday> fetchAllHolidays() {
        String query = "select * from holidays";
        return this.jdbcTemplate.query(query, holidayRowMapper);
    }

    public List<Holiday> fetchHolidaysByType(String holidayType) {
        String query = "select * from holidays where type = ?";
        return jdbcTemplate.query(query, ps -> ps.setString(1, holidayType), holidayRowMapper);

    }
}
