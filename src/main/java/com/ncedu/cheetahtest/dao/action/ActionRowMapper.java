package com.ncedu.cheetahtest.dao.action;


import com.ncedu.cheetahtest.entity.action.Action;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ActionRowMapper implements RowMapper<Action> {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String TYPE = "type";
    public static final String DESCRIPTION = "description";


    @Override
    public Action mapRow(ResultSet rs, int i) throws SQLException {
        Action action = new Action();

        action.setId(rs.getInt(ID));
        action.setTitle(rs.getString(TITLE));
        action.setType(rs.getString(TYPE));
        action.setDescription(rs.getString(DESCRIPTION));

        return action;
    }
}
