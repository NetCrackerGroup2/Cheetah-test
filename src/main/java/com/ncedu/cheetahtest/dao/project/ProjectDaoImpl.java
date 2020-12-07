package com.ncedu.cheetahtest.dao.project;

import com.ncedu.cheetahtest.dao.genericdao.AbstractDaoImpl;
import com.ncedu.cheetahtest.entity.project.Project;
import com.ncedu.cheetahtest.entity.project.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ProjectDaoImpl extends AbstractDaoImpl<Project> implements ProjectDao {
    private final JdbcTemplate jdbcTemplate;
    private static final String[] rows = {"id", "title", "link", "status", "create_date"};


    @Autowired
    public ProjectDaoImpl(JdbcTemplate jdbcTemplate) {
        super(new ProjectMapper(), jdbcTemplate, rows, "project");
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createProject(ProjectDto projectDto) {
        String sqlQueryForProjectTable = ProjectSqlConsts.CREATE_PROJECT_QUERY;
        String sqlQueryForUserProjectTable = ProjectSqlConsts.CREATE_USER_PROJECT_QUERY;

        jdbcTemplate.update(
                sqlQueryForProjectTable,
                projectDto.getProject().getTitle(),
                projectDto.getProject().getLink(),
                "ACTIVE",
                Timestamp.valueOf(LocalDateTime.now())
        );

        List<Project> project = jdbcTemplate.query(
            ProjectSqlConsts.SELECT_PROJECT_BY_TITLE_QUERY,
            p -> p.setString(1, projectDto.getProject().getTitle()),
            new ProjectMapper()
        );

        int id = project.get(0).getId();

        for (int watcherId: projectDto.getWatcherIds()) {
            jdbcTemplate.update(
                    sqlQueryForUserProjectTable,
                    id,
                    watcherId,
                    "WATCHER"
            );
        }
    }

    @Override
    public void setArchievedStatus(int id) {
        String sqlQuery = ProjectSqlConsts.SET_ARCHIEVED_STATUS_TO_PROJECT_QUERY;
        jdbcTemplate.update(sqlQuery, id);
    }

    @Override
    public List<Project> getAllArchievedProjects() {
        String sqlQuery = ProjectSqlConsts.SELECT_ALL_ARCHIEVED_PROJECTS_QUERY;
        return jdbcTemplate.query(sqlQuery, new ProjectMapper());
    }

    @Override
    public Project findByProjectId(int id) {
        String sqlQuery = ProjectSqlConsts.SELECT_PROJECT_BY_ID_QUERY;
        List<Project> projects = jdbcTemplate.query(
                sqlQuery,
                preparedStatement -> preparedStatement.setInt(1, id),
                new ProjectMapper()
        );

        if (projects.size() == 1) return projects.get(0);

        return null;
    }

    @Override
    public void updateProjectById(int id, Project project) {
        String sqlQuery = ProjectSqlConsts.UPDATE_PROJECT_QUERY;

        jdbcTemplate.update(
                sqlQuery,
                project.getTitle(),
                project.getLink(),
                id
        );

    }
}
