package com.ncedu.cheetahtest.dao.actscenario;

import com.ncedu.cheetahtest.entity.actscenario.ActScenario;
import com.ncedu.cheetahtest.entity.actscenario.ActStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static com.ncedu.cheetahtest.dao.actscenario.ActScenarioConsts.*;

@Repository
public class ActScenarioDaoImpl implements ActScenarioDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ActScenarioDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ActScenario createActScenario(ActScenario actScenario) {
        jdbcTemplate.update(
                CREATE_ACT_SCENARIO,
                actScenario.getActionId(),
                actScenario.getTestScenarioId(),
                actScenario.getPriority(),
                actScenario.getActStatus().toString(),
                actScenario.getParameterId());
        return findBySignature(actScenario);
    }

    @Override
    public ActScenario editActScenario(ActScenario actScenario, int id) {
        jdbcTemplate.update(
                EDIT_ACT_SCENARIO,
                actScenario.getPriority(),
                actScenario.getActStatus().toString(),
                actScenario.getParameterId(),
                id);
        return this.findById(id);
    }

    @Override
    public List<ActScenario> findByTitleLike(String title, int limit, int offset) {
        return jdbcTemplate.query(
                FIND_BY_TITLE_LIKE,
                preparedStatement -> {
                    preparedStatement.setString(1, title);
                    preparedStatement.setInt(2, limit);
                    preparedStatement.setInt(3, offset);
                },
                new ActScenarioRowMapper()
        );
    }

    @Override
    public List<ActScenario> findAllByTitleLike(String title) {
        return jdbcTemplate.query(
                FIND_ALL_BY_TITLE_LIKE,
                preparedStatement -> preparedStatement.setString(1, title),
                new ActScenarioRowMapper()
        );
    }

    @Override
    public List<ActScenario> findAllByTitleInTestScenario(String title, int idTestScenario) {
        return jdbcTemplate.query(
                FIND_ALL_BY_TITLE_IN_TEST_SCENARIO,
                preparedStatement -> {
                    preparedStatement.setString(1, title);
                    preparedStatement.setInt(2, idTestScenario);
                },
                new ActScenarioRowMapper()
        );
    }

    @Override
    public List<ActScenario> findByTitleInTestScenario(String title, int idTestScenario, int limit, int offset) {
        return jdbcTemplate.query(
                FIND_BY_TITLE_IN_TEST_SCENARIO,
                preparedStatement -> {
                    preparedStatement.setString(1, title);
                    preparedStatement.setInt(2, idTestScenario);
                    preparedStatement.setInt(3, limit);
                    preparedStatement.setInt(4, offset);
                },
                new ActScenarioRowMapper()
        );
    }

    @Override
    public void deleteActScenario(int id) {
        jdbcTemplate.update(DELETE_ACT_SCENARIO, id);
    }


    @Override
    public ActScenario setStatus(ActStatus actStatus, int id) {
        jdbcTemplate.update(
                SET_STATUS,
                actStatus.toString(),
                id
        );
        return this.findById(id);
    }

    @Override
    public ActScenario findById(int id) {
        List<ActScenario> actScenarios = jdbcTemplate.query(
                FIND_BY_ID,
                preparedStatement -> preparedStatement.setInt(1, id),
                new ActScenarioRowMapper());
        if (actScenarios.size() == 1) {
            return actScenarios.get(0);
        } else {
            return null;
        }
    }

    @Override
    public int totalFindByTitleLike(String title) {
        List<Integer> counts = jdbcTemplate.query(
                TOTAL_FIND_BY_TITLE_LIKE,
                preparedStatement -> preparedStatement.setString(1, title),
                new CountActScenarioRowMapper());
        if (counts.size() == 1) {
            return counts.get(0);
        } else {
            return 0;
        }
    }

    @Override
    public int totalFindByTitleInTestScenario(String title, int idTestScenario) {
        List<Integer> counts = jdbcTemplate.query(
                TOTAL_FIND_BY_TITLE_IN_TEST_SCENARIO,
                preparedStatement -> {
                    preparedStatement.setString(1, title);
                    preparedStatement.setInt(2, idTestScenario);
                },
                new CountActScenarioRowMapper());
        if (counts.size() == 1) {
            return counts.get(0);
        } else {
            return 0;
        }
    }

    @Override
    public void setStatusForAllByIdTestScenario(ActStatus actStatus, int idTestScenario) {
        jdbcTemplate.update(STATUS_FOR_ALL_BY_ID_TEST_SCENARIO, actStatus.toString(), idTestScenario);
    }

    @Override
    public void deleteAllByIdTestScenario(int idTestScenario) {
        jdbcTemplate.update(
                DELETE_ALL_BY_ID_TEST_SCENARIO,
                idTestScenario);
    }

    @Override
    public void deleteAllByIdCompScenario(int idCompScenario) {
        jdbcTemplate.update(DELETE_ALL_BY_ID_COMP_SCENARIO, idCompScenario);
    }

    @Override
    public ActScenario findBySignature(ActScenario actScenario) {
        List<ActScenario> actScenarios = jdbcTemplate.query(
                FIND_BY_SIGNATURE,
                preparedStatement -> {
                    preparedStatement.setInt(1, actScenario.getActionId());
                    preparedStatement.setInt(2, actScenario.getTestScenarioId());
                    preparedStatement.setInt(3, actScenario.getPriority());
                },
                new ActScenarioRowMapper()
        );
        if (actScenarios.size() == 1) {
            return actScenarios.get(0);
        } else return null;
    }
}
