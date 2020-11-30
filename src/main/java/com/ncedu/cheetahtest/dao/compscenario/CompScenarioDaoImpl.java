package com.ncedu.cheetahtest.dao.compscenario;

import com.ncedu.cheetahtest.dao.actscenario.ActScenarioRowMapper;
import com.ncedu.cheetahtest.entity.actscenario.ActScenario;
import com.ncedu.cheetahtest.entity.actscenario.ActStatus;
import com.ncedu.cheetahtest.entity.compscenario.CompScenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static com.ncedu.cheetahtest.dao.compscenario.CompScenarioConsts.*;

@Repository
public class CompScenarioDaoImpl implements CompScenarioDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CompScenarioDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public CompScenario createCompScenario(CompScenario compScenario) {
        jdbcTemplate.update(
                CREATE_COMP_SCENARIO,
                compScenario.getIdCompound(),
                compScenario.getIdTestScenario(),
                compScenario.getPriority(),
                compScenario.getStatus().toString()
        );
        return findBySignature(compScenario);

    }

    @Override
    public CompScenario editCompScenario(CompScenario compScenario, int id) {
        jdbcTemplate.update(
                EDIT_COMP_SCENARIO,
                compScenario.getIdCompound(),
                compScenario.getIdTestScenario(),
                compScenario.getPriority(),
                compScenario.getStatus().toString(),
                id
        );
        return findBySignature(compScenario);
    }

    @Override
    public void deleteCompScenario(int id) {
        jdbcTemplate.update(DELETE_COMP_SCENARIO, id);
    }

    @Override
    public List<CompScenario> findByTitleLike(String title, int limit, int offset) {
        return jdbcTemplate.query(
                FIND_BY_TITLE_LIKE,
                preparedStatement -> {
                    preparedStatement.setString(1, title);
                    preparedStatement.setInt(2, limit);
                    preparedStatement.setInt(3, offset);
                },
                new CompScenarioRowMapper()
        );
    }

    @Override
    public List<CompScenario> findByTitleInTestScenario(String title, int idTestScenario, int limit, int offset) {
        return jdbcTemplate.query(
                FIND_BY_TITLE_IN_TEST_SCENARIO,
                preparedStatement -> {
                    preparedStatement.setString(1, title);
                    preparedStatement.setInt(2, idTestScenario);
                    preparedStatement.setInt(3, limit);
                    preparedStatement.setInt(4, offset);
                },
                new CompScenarioRowMapper()
        );
    }

    @Override
    public List<CompScenario> findAllByTitleLike(String title) {
        return jdbcTemplate.query(
                FIND_ALL_BY_TITLE_LIKE,
                preparedStatement -> preparedStatement.setString(1, title),
                new CompScenarioRowMapper()
        );
    }

    @Override
    public List<CompScenario> findAllByTitleInTestScenario(String title, int idTestScenario) {
        return jdbcTemplate.query(
                FIND_ALL_BY_TITLE_IN_TEST_SCENARIO,
                preparedStatement -> {
                    preparedStatement.setString(1, title);
                    preparedStatement.setInt(2, idTestScenario);
                },
                new CompScenarioRowMapper()
        );
    }

    @Override
    public int totalFindByTitleLike(String title) {
        List<Integer> counts = jdbcTemplate.query(
                TOTAL_FIND_BY_TITLE_LIKE,
                preparedStatement -> preparedStatement.setString(1, title),
                new CountCompScenarioRowMapper()
        );
        if (counts.size() == 1) {
            return counts.size();
        } else return 0;
    }

    @Override
    public int totalFindByTitleInTestScenario(String title, int idTestScenario) {
        List<Integer> counts = jdbcTemplate.query(
                TOTAL_FIND_BY_TITLE_IN_TEST_SCENARIO,
                preparedStatement -> {
                    preparedStatement.setString(1, title);
                    preparedStatement.setInt(2, idTestScenario);
                },
                new CountCompScenarioRowMapper()
        );
        if (counts.size() == 1) {
            return counts.size();
        } else return 0;
    }

    @Override
    public void setStatusForAllByIdTestScenario(ActStatus actStatus, int idTestScenario) {
        jdbcTemplate.update(
                SET_STATUS_FOR_ALL_BY_ID_TEST_SCENARIO,
                actStatus.toString(),
                idTestScenario
        );

    }

    @Override
    public List<ActScenario> getAllActionScenarioInComp(int id) {
        return jdbcTemplate.query(
                GET_ALL_ACTION_SCENARIO_IN_COMP,
                preparedStatement -> preparedStatement.setInt(1, id),
                new ActScenarioRowMapper()
        );
    }

    @Override
    public CompScenario findById(int id) {
        List<CompScenario> compScenarios = jdbcTemplate.query(
                FIND_BY_ID,
                preparedStatement -> preparedStatement.setInt(1, id),
                new CompScenarioRowMapper()
        );
        if (compScenarios.size() == 1) {
            return compScenarios.get(0);
        } else {
            return null;
        }
    }

    @Override
    public CompScenario findBySignature(CompScenario compScenario) {
        List<CompScenario> compScenarios = jdbcTemplate.query(
                FIND_BY_SIGNATURE,
                preparedStatement -> {
                    preparedStatement.setInt(1, compScenario.getIdCompound());
                    preparedStatement.setInt(2, compScenario.getIdTestScenario());
                    preparedStatement.setInt(3, compScenario.getPriority());
                },
                new CompScenarioRowMapper()
        );
        if (compScenarios.size() == 1) {
            return compScenarios.get(0);
        } else return null;
    }

    @Override
    public void deleteAllByIdTestScenario(int idTestScenario) {
        jdbcTemplate.update(
                DELETE_ALL_BY_TEST_SCENARIO,
                idTestScenario);

    }

}
