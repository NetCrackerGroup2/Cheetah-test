package com.ncedu.cheetahtest.dao.testcase;

import com.ncedu.cheetahtest.entity.testcase.TestCase;
import com.ncedu.cheetahtest.entity.testcase.TestCaseResult;
import com.ncedu.cheetahtest.entity.testcase.TestCaseStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TestCaseMapper implements RowMapper<TestCase> {

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String PROJECT_ID = "project_id";
    public static final String STATUS = "status";
    public static final String RESULT = "result";

    @Override
    public TestCase mapRow(ResultSet resultSet, int i) throws SQLException {
        TestCase testCase = new TestCase();

        testCase.setId(resultSet.getInt(ID));
        testCase.setTitle(resultSet.getString(TITLE));
        testCase.setProjectId(resultSet.getInt(PROJECT_ID));
        testCase.setStatus(TestCaseStatus.valueOf(resultSet.getString(STATUS)));
        testCase.setResult(TestCaseResult.valueOf(resultSet.getString(RESULT)));

        return testCase;
    }
}
