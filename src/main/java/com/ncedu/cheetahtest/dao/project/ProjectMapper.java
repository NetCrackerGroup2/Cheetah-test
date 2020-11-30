package com.ncedu.cheetahtest.dao.project;

import com.ncedu.cheetahtest.entity.project.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMapper implements RowMapper<Project> {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String LINK = "link";
    private static final String STATUS = "status";
    private static final String CREATE_DATE = "create_date";

    @Override
    public Project mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Project(
                resultSet.getInt(ID),
                resultSet.getString(TITLE),
                resultSet.getString(LINK),
                resultSet.getString(STATUS),
                resultSet.getTimestamp(CREATE_DATE)
        );
    }
}
