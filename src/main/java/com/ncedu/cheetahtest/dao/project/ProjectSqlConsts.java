package com.ncedu.cheetahtest.dao.project;

public final class ProjectSqlConsts {
    public static final String CREATE_PROJECT_QUERY      = "INSERT INTO project (title, link, status, create_date) " +
                                                           "VALUES (?, ?, ?::project_status, ?)";

    public static final String CREATE_USER_PROJECT_QUERY = "INSERT INTO user_project (project_id, user_id, user_status) " +
                                                           "VALUES ((SELECT id FROM project WHERE id = ?), ?, ?::user_project_status)";

    public static final String SELECT_ALL_PROJECTS_QUERY = "SELECT id, title, link, status, create_date " +
                                                           "FROM project";

    public static final String SELECT_PROJECT_BY_TITLE_QUERY = SELECT_ALL_PROJECTS_QUERY + " WHERE title = ?";

    public static final String SELECT_ALL_ARCHIEVED_PROJECTS_QUERY = SELECT_ALL_PROJECTS_QUERY +
                                                                     " WHERE status = 'INACTIVE'";

    public static final String SET_ARCHIEVED_STATUS_TO_PROJECT_QUERY = "UPDATE project SET status = 'INACTIVE' " +
                                                                       "WHERE id = ?";

    public static final String SELECT_PROJECT_BY_ID_QUERY = SELECT_ALL_PROJECTS_QUERY + " WHERE id = ?";
}
