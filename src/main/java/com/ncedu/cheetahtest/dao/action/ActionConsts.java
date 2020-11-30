package com.ncedu.cheetahtest.dao.action;

public class ActionConsts {

    public static final String SELECT_ACTIONS_BY_TITLE_LIKE =
            "SELECT action.id,action.title,action.type,description " +
                    "FROM action " +
                    "WHERE title LIKE CONCAT('%',?,'%') ORDER BY title limit ? offset ?";
    public static final String GET_TOTAL_ELEMENTS = "SELECT count(*) FROM action WHERE title LIKE CONCAT('%',?,'%')";
    public static final String GET_ACTIONS_IN_COMPOUND = "SELECT action.id, action.title, action.type, action.description " +
            "FROM action INNER JOIN comp_act_prior ON action.id = comp_act_prior.action_id " +
            "INNER JOIN compound c ON comp_act_prior.comp_id = c.id " +
            "WHERE c.id = ? ORDER BY comp_act_prior.priority " +
            "LIMIT ? OFFSET ?";
    public static final String GET_TOTAL_ACTIONS_IN_COMP = "SELECT COUNT(*)" +
            "FROM action INNER JOIN comp_act_prior ON action.id = comp_act_prior.action_id " +
            "INNER JOIN compound c ON comp_act_prior.comp_id = c.id " +
            "WHERE c.id = ? ";
    public static final String GET_ACTION_BY_ID = "SELECT id, title, type,description FROM action WHERE id = ?";
    public static final String GET_ACTION_BY_TITLE = "SELECT id, title, type,description FROM action WHERE title = ?";
    public static final String EDIT_ACTION_DESC  = "UPDATE action SET description = ? WHERE id = ?";
    public static final String SELECT_ALL_ACTIONS_BY_TITLE_LIKE = "SELECT action.id,action.title,action.type,description " +
            "FROM action WHERE title LIKE CONCAT('%',?,'%') LIMIT 10";
    public static final String GET_ALL_ACTIONS_IN_COMP = "SELECT action.id, action.title, action.type, action.description " +
            "FROM action INNER JOIN comp_act_prior ON action.id = comp_act_prior.action_id " +
            "INNER JOIN compound c ON comp_act_prior.comp_id = c.id " +
            "WHERE c.id = ? ORDER BY comp_act_prior.priority ";
    public static final String GET_ACTIONS_BY_TYPE = "SELECT id,title,type,description " +
            "FROM action WHERE type LIKE CONCAT('%',?,'%') LIMIT ? OFFSET ?";
    public static final String GET_TOTAL_ACTIONS_BY_TYPE = "SELECT COUNT(*) FROM action WHERE type LIKE CONCAT('%',?,'%')";

}
