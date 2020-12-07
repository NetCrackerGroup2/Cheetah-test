package com.ncedu.cheetahtest.dao.action;


import com.ncedu.cheetahtest.entity.action.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static com.ncedu.cheetahtest.dao.action.ActionConsts.*;


@Repository
public class ActionDaoImpl implements ActionDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ActionDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Action> selectActionsByTitleLike(String title, int limit, int offset) {
        return jdbcTemplate.query(
                SELECT_ACTIONS_BY_TITLE_LIKE,
                preparedStatement -> {
                    preparedStatement.setString(1, title);
                    preparedStatement.setInt(2, limit);
                    preparedStatement.setInt(3, offset);
                },
                new ActionRowMapper());
    }

    @Override
    public int getTotalElements(String title) {
        List<Integer> totalElements = jdbcTemplate.query(
                GET_TOTAL_ELEMENTS,
                preparedStatement -> preparedStatement.setString(1, title),
                new CountActionRowMapper()
        );
        if (totalElements.size() == 1) {
            return totalElements.get(0);
        } else return 0;


    }

    @Override
    public List<Action> getActionsInCompound(int idCompound, int limit, int offset) {
        return jdbcTemplate.query(
                GET_ACTIONS_IN_COMPOUND,
                preparedStatement -> {
                    preparedStatement.setInt(1, idCompound);
                    preparedStatement.setInt(2, limit);
                    preparedStatement.setInt(3, offset);
                },
                new ActionRowMapper()
        );
    }

    @Override
    public int getTotalActionsInComp(int idCompound) {
        List<Integer> counts = jdbcTemplate.query(
                GET_TOTAL_ACTIONS_IN_COMP,
                preparedStatement -> preparedStatement.setInt(1, idCompound),
                new CountActionRowMapper()
        );
        if (counts.size() == 1) {
            return counts.get(0);
        } else {
            return 0;
        }
    }

    @Override
    public Action getActionByTitle(String title) {
        List<Action> actions = jdbcTemplate.query(
                GET_ACTION_BY_TITLE,
                preparedStatement -> preparedStatement.setString(1, title),
                new ActionRowMapper()
        );
        if (actions.size() == 1) {
            return actions.get(0);
        } else return null;
    }


    @Override
    public Action getActionById(int id) {
        List<Action> actions = jdbcTemplate.query(
                GET_ACTION_BY_ID,
                preparedStatement -> preparedStatement.setInt(1, id),
                new ActionRowMapper()
        );
        if (actions.size() == 1) {
            return actions.get(0);
        } else return null;
    }

    @Override
    public Action editActionDesc(String description, int id) {
        jdbcTemplate.update(
                EDIT_ACTION_DESC,
                preparedStatement -> {
                    preparedStatement.setString(1, description);
                    preparedStatement.setInt(2, id);
                });
        return this.getActionById(id);
    }

    @Override
    public List<Action> selectAllActionsByTitleLike(String title) {
        return jdbcTemplate.query(
                SELECT_ALL_ACTIONS_BY_TITLE_LIKE,
                preparedStatement -> preparedStatement.setString(1, title),
                new ActionRowMapper());
    }

    @Override
    public List<Action> getAllActionsInComp(int idComp) {
        return jdbcTemplate.query(
                GET_ALL_ACTIONS_IN_COMP,
                preparedStatement -> preparedStatement.setInt(1, idComp),
                new ActionRowMapper()
        );
    }

    @Override
    public List<Action> getActionsByType(String type, int limit, int offset) {
        return jdbcTemplate.query(
                GET_ACTIONS_BY_TYPE,
                preparedStatement -> {
                    preparedStatement.setString(1, type);
                    preparedStatement.setInt(2, limit);
                    preparedStatement.setInt(3, offset);

                },
                new ActionRowMapper());
    }

    @Override
    public List<Action> getActionsInCompoundNotPaginated(int idCompound) {
        return jdbcTemplate.query(
                GET_ACTIONS_IN_COMPOUND_NOT_PAGINATED,
                preparedStatement -> preparedStatement.setInt(1, idCompound),
                new ActionRowMapper()
        );
    }

    @Override
    public int getTotalActionsByType(String type) {
        List<Integer> counts = jdbcTemplate.query(
                GET_TOTAL_ACTIONS_BY_TYPE,
                preparedStatement -> preparedStatement.setString(1, type),
                new CountActionRowMapper()
        );
        if (counts.size() == 1) {
            return counts.get(0);
        } else {
            return 0;
        }
    }
}
