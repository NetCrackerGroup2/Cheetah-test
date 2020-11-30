package com.ncedu.cheetahtest.dao.parameters;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountParametersRowMapper implements RowMapper<Integer> {
    public static final String COUNT = "count";

    @Override
    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getInt(COUNT);
    }
}
