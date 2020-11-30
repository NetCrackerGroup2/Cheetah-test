package com.ncedu.cheetahtest.dao.user;

public class UserConsts {
    public static final String CREATE_DEVELOPER_SQL = "INSERT INTO users (email, password, name, role, status) VALUES (?,?,?,?::user_role,?::user_status)";
    public static final String FIND_USER_BY_EMAIL_SQL = "SELECT id, email, password, name, role, status, last_request FROM users WHERE email = ? LIMIT 1";
    public static final String FIND_USER_BY_EMAIL_AND_PASSWORD_SQL = "SELECT id, email, password, name, role, status, last_request FROM users WHERE email = ? AND password = ? LIMIT 1";
    public static final String CHANGE_USER_PASSWORD_SQL = "UPDATE users SET password = ? WHERE id = ?";
    public static final String FIND_USER_BY_TOKEN_SQL = "SELECT id, email, password, name, role, status, last_request FROM users WHERE id IN (SELECT user_id FROM reset_token WHERE token = ?) LIMIT 1";
    public static final String SET_USER_LAST_REQUEST_SQL = "UPDATE users SET last_request = ? WHERE email = ?";
    public static final String EDIT_USER_SQL = "UPDATE users SET email = ?, name = ?, role = ?::user_role where id = ?";
    public static final String CHANGE_USER_STATUS_SQL = "UPDATE users SET status = ?::user_status where id = ?";
    public static final String FIND_USER_BY_ID_SQL = "SELECT id, email, password, name, role, status, last_request FROM users WHERE id = ? LIMIT 1";
}
