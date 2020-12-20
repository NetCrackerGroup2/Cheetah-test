package com.ncedu.cheetahtest.dao.dashboard;

public class DashboardConsts {
    public static final String COUNT_ACTIVE_USER_BY_ROLE_SQL = "SELECT count(*) FROM users WHERE role::text LIKE ? AND status='ACTIVE';";
    public static final String GET_ALL_USER_ACTIVITY_SQL ="SELECT name, role, last_request, photo_url FROM users WHERE last_request > current_timestamp - ?::INTERVAL ORDER BY last_request DESC ;";
    public static final String GET_USER_ACTIVITY_FOR_MANAGER_SQL ="SELECT name, role, last_request, photo_url FROM users WHERE last_request > current_timestamp - ?::INTERVAL AND role <> 'ADMIN' ORDER BY last_request DESC ;";

}
