package com.ncedu.cheetahtest.dao.testscenario;

import com.ncedu.cheetahtest.dao.genericdao.AbstractDaoImpl;
import com.ncedu.cheetahtest.entity.testscenario.ItemDTO;
import com.ncedu.cheetahtest.entity.testscenario.TestScenario;
import com.ncedu.cheetahtest.exception.general.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ncedu.cheetahtest.dao.testscenario.TestScenarioConsts.*;

@Repository
public class TestScenarioDaoImpl extends AbstractDaoImpl<TestScenario> implements TestScenarioDao {

    private static final String[] rows = {"id", "title", "description", "status", "test_case_id"};

    @Autowired
    public TestScenarioDaoImpl(JdbcTemplate jdbcTemplate) {
        super(new TestScenarioMapper(), jdbcTemplate, rows, "test_scenario");
    }

    @Override
    public List<TestScenario> findByTitleLike(String title, int idTestCase, int limit, int offset) {
        return jdbcTemplate.query(
                FIND_BY_TITLE_LIKE,
                preparedStatement -> {
                    preparedStatement.setString(1, title);
                    preparedStatement.setInt(2, idTestCase);
                    preparedStatement.setInt(3, limit);
                    preparedStatement.setInt(4, offset);
                },
                new TestScenarioMapper());
    }

    @Override
    public List<TestScenario> findTestScenariosFromTestCase(int idTestCase, int limit, int offset) {
        return jdbcTemplate.query(
                FIND_SCENARIOS_FROM_TEST_CASE,
                preparedStatement -> {
                    preparedStatement.setInt(1, idTestCase);
                    preparedStatement.setInt(2, limit);
                    preparedStatement.setInt(3, offset);
                },
                new TestScenarioMapper()
        );
    }

    @Override
    public TestScenario createTestScenario(TestScenario testScenario) {
        jdbcTemplate.update(
                CREATE_TEST_SCENARIO,
                testScenario.getTitle(),
                testScenario.getDescription(),
                testScenario.getStatus().toString(),
                testScenario.getIdTestCase()
        );
        return this.findByTitle(testScenario.getTitle());
    }

    @Override
    public TestScenario editTestScenario(TestScenario testScenario, int id) {
        jdbcTemplate.update(
                EDIT_TEST_SCENARIO,
                testScenario.getTitle(),
                testScenario.getDescription(),
                testScenario.getStatus().toString(),
                testScenario.getIdTestCase(),
                id
        );
        return findByTitle(testScenario.getTitle());
    }

    @Override
    public void deleteTestScenario(int id) {
        jdbcTemplate.update(DELETE_TEST_SCENARIO, id);
    }

    @Override
    public int getTotalElements(int idTestCase, String title) {
        List<Integer> count = jdbcTemplate.query(
                GET_TOTAL_ELEMENTS_FROM_SEARCH,
                preparedStatement -> {
                    preparedStatement.setInt(1, idTestCase);
                    preparedStatement.setString(2, title);
                },
                new CountTestScenarioRowMapper());
        if (count.size() == 1) return count.get(0);
        else return 0;
    }

    @Override
    public int getTestScenariosFromTestCaseAmount(int idTestCase) {
        List<Integer> count = jdbcTemplate.query(
                GET_SCENARIOS_FROM_TEST_CASE_AMOUNT,
                preparedStatement -> {
                    preparedStatement.setInt(1, idTestCase);
                },
                new CountTestScenarioRowMapper());
        if (count.size() == 1) return count.get(0);
        else return 0;
    }

    @Override
    public TestScenario findByTitle(String title) {
        List<TestScenario> testScenarios = jdbcTemplate.query(
                FIND_BY_TITLE,
                preparedStatement -> preparedStatement.setString(1, title),
                new TestScenarioMapper()
        );
        if (testScenarios.size() == 1) {
            return testScenarios.get(0);
        } else return null;
    }

    @Override
    public void deactivate(int id) {
        int result = jdbcTemplate.update(DEACTIVATE_TEST_SCENARIO, id);
        if (result != 1) {
            throw new EntityNotFoundException("Exception in " + getClass().getName());
        }
    }

    @Override
    public TestScenario findTestScenarioByTitleExceptCurrent(String title, int id) {
        List<TestScenario> testScenarios = jdbcTemplate.query(
                FIND_TEST_SCENARIO_BY_TITLE_EXCEPT_CURRENT,
                preparedStatement -> {
                    preparedStatement.setString(1, title);
                    preparedStatement.setInt(2, id);
                },
                new TestScenarioMapper()
        );

        if (testScenarios.size() == 1) {
            return testScenarios.get(0);
        }

        return null;
    }

    @Override
    public int getAllItemsAmount(int idTestScenario) {
        List<Integer> count = jdbcTemplate.query(
                GET_ALL_ITEMS_AMOUNT,
                preparedStatement -> {
                    preparedStatement.setInt(1, idTestScenario);
                    preparedStatement.setInt(2, idTestScenario);
                },
                new CountTestScenarioRowMapper());
        if (count.size() == 1) return count.get(0);
        else return 0;
    }

    @Override
    public List<ItemDTO> getAllItems(int idTestScenario, int limit, int offset) {
        return jdbcTemplate.query(
                GET_ALL_ITEMS,
                preparedStatement -> {
                    preparedStatement.setInt(1, idTestScenario);
                    preparedStatement.setInt(2, idTestScenario);
                    preparedStatement.setInt(3, limit);
                    preparedStatement.setInt(4, offset);
                },
                new ItemMapper()
        );
    }

}
