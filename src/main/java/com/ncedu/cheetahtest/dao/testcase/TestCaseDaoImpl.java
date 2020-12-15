package com.ncedu.cheetahtest.dao.testcase;

import com.ncedu.cheetahtest.dao.genericdao.AbstractDaoImpl;
import com.ncedu.cheetahtest.entity.testcase.TestCase;
import com.ncedu.cheetahtest.exception.general.EntityNotFoundException;
import com.ncedu.cheetahtest.exception.testcase.TestCaseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static com.ncedu.cheetahtest.dao.testcase.TestCaseConsts.*;

@Repository
public class TestCaseDaoImpl extends AbstractDaoImpl<TestCase> implements TestCaseDao {

    private static final String[] rows = {"id", "title", "project_id", "status", "result"};

    @Autowired
    public TestCaseDaoImpl(JdbcTemplate jdbcTemplate) {
        super(new TestCaseMapper(), jdbcTemplate, rows, "test_case");
    }

    @Override
    public void save(TestCase testCase) {
        int result = jdbcTemplate.update(UPDATE_TEST_CASE_SQL,
                testCase.getTitle(),
                testCase.getProjectId(),
                testCase.getStatus().toString(),
                testCase.getResult().toString(),
                testCase.getId());

        if (result != 1) {
            throw new TestCaseNotFoundException();
        }
    }

    @Override
    public TestCase findTestCaseByProjectIdAndTestCaseId(int projectId, int id) {
        List<TestCase> testCase = jdbcTemplate.query(
                FIND_TEST_CASE_BY_PROJECT_ID_AND_TEST_CASE_ID,
                preparedStatement -> {
                    preparedStatement.setInt(1, projectId);
                    preparedStatement.setInt(2, id);
                },
                new TestCaseMapper()
        );

        if (testCase.size() == 1) {
            return testCase.get(0);
        }

        return null;
    }

    @Override
    public TestCase findTestCaseByTitleExceptCurrent(String title, int id) {
        List<TestCase> testCases = jdbcTemplate.query(
                FIND_TEST_CASE_BY_TITLE_EXCEPT_CURRENT,
                preparedStatement -> {
                    preparedStatement.setString(1, title);
                    preparedStatement.setInt(2, id);
                },
                new TestCaseMapper()
        );

        if (testCases.size() == 1) {
            return testCases.get(0);
        }

        return null;
    }

    @Override
    public int createTestCase(TestCase testCase) {
        jdbcTemplate.update(
                CREATE_TEST_CASE_SQL,
                testCase.getTitle(),
                testCase.getProjectId(),
                testCase.getStatus().toString(),
                testCase.getResult().toString()
        );

        return getSingleIntElement(testCase.getTitle(), GET_TEST_CASE_ID_BY_TITLE);
    }

    @Override
    public void deactivate(int id) {
        int result = jdbcTemplate.update(
                DEACTIVATE_TEST_CASE_SQL,
                id);
        if (result != 1) {
            throw new EntityNotFoundException("Exception in " + getClass().getName());
        }
    }

    @Override
    public List<TestCase> getActiveTestCasesPaginatedByProjectId(int page, int size, int projectId) {
        int offset = getOffset(page, size);

        return jdbcTemplate.query(
                GET_TEST_CASE_PAGINATED_BY_PROJECT_ID,
                preparedStatement -> {
                    preparedStatement.setInt(1, projectId);
                    preparedStatement.setInt(2, size);
                    preparedStatement.setInt(3, offset);
                },
                rowMapper
        );
    }

    @Override
    public int getAmountActiveElementsByProjectId(int projectId) {
        List<Integer> amount = jdbcTemplate.query(
                GET_AMOUNT_OF_ACTIVE_TEST_CASES_BY_PROJECT_ID,
                preparedStatement -> preparedStatement.setInt(1, projectId),
                (resultSet, i) -> resultSet.getInt(1)
        );

        if (amount.size() == 1) {
            return amount.get(0);
        }

        return 0;
    }

    @Override
    public List<TestCase> findTestCasesByTitlePaginatedAndByProjectId(int page, int size, String keyword, int projectId) {
        int offset = getOffset(page, size);

        return jdbcTemplate.query(
                FIND_BY_TITLE_TEST_CASE_PAGINATED_BY_PROJECT_ID,
                preparedStatement -> {
                    preparedStatement.setInt(1, projectId);
                    preparedStatement.setString(2, "%" + keyword + "%");
                    preparedStatement.setInt(3, size);
                    preparedStatement.setInt(4, offset);
                },
                rowMapper
        );
    }

    @Override
    public int getAmountByTitlePaginatedAndByProjectId(String keyword, int projectId) {
        List<Integer> amount = jdbcTemplate.query(
                GET_AMOUNT_OF_ACTIVE_TEST_CASES_BY_PROJECT_ID_AND_ILIKE,
                preparedStatement -> {
                    preparedStatement.setString(1, "%" + keyword + "%");
                    preparedStatement.setInt(2, projectId);
                },
                (resultSet, i) -> resultSet.getInt(1)
        );

        if (amount.size() == 1) {
            return amount.get(0);
        }

        return 0;
    }

    @Override
    public boolean getTestCaseRepeatable(int id) {
        return jdbcTemplate.queryForObject(CHECK_REPEATEBLE_TEST_CASE, new Object[] {id},
                Boolean.class);
    }

    @Override
    public String getExecutionDateById(int id) {
        return jdbcTemplate.queryForObject(GET__EXECUTION_DATE_BY_ID, new Object[] {id},
                String.class);
    }
}
