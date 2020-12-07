package com.ncedu.cheetahtest.dao.testscenario;

import com.ncedu.cheetahtest.entity.testscenario.StatusTestScenario;
import com.ncedu.cheetahtest.entity.testscenario.TestScenario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestScenarioMapper implements RowMapper<TestScenario> {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String TEST_CASE_ID = "test_case_id";
    public static final String STATUS = "status";

    @Override
    public TestScenario mapRow(ResultSet resultSet, int i) throws SQLException {
        TestScenario testScenario = new TestScenario();

        testScenario.setId(resultSet.getInt(ID));
        testScenario.setTitle(resultSet.getString(TITLE));
        testScenario.setDescription(resultSet.getString(DESCRIPTION));
        testScenario.setStatus(StatusTestScenario.valueOf(resultSet.getString(STATUS)));
        testScenario.setIdTestCase(resultSet.getInt(TEST_CASE_ID));

        return testScenario;
    }
}
