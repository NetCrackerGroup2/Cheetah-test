package com.ncedu.cheetahtest.dao.dataset;

import com.ncedu.cheetahtest.entity.dataset.DataSet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSetRowMapper implements RowMapper<DataSet> {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String IDTESTCASE = "test_case_id";

    @Override
    public DataSet mapRow(ResultSet resultSet, int i) throws SQLException {
        DataSet dataSet = new DataSet();
        dataSet.setId(resultSet.getInt(ID));
        dataSet.setTitle(resultSet.getString(TITLE));
        dataSet.setDescription(resultSet.getString(DESCRIPTION));
        dataSet.setIdTestCase(resultSet.getInt(IDTESTCASE));
        return dataSet;
    }
}