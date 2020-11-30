package com.ncedu.cheetahtest.dao.action;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CountActionRowMapper implements RowMapper<Integer> {
    private static final String COUNT = "count";

    @Override
    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getInt(COUNT);
    }
}
