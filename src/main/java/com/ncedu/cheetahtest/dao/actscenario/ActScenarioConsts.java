package com.ncedu.cheetahtest.dao.actscenario;

public class ActScenarioConsts {
    public static final String CREATE_ACT_SCENARIO = "INSERT INTO act_scenario (action_id, test_scenario_id, priority, action_status, param_id) " +
            "VALUES (?,?,?,?::action_status,?)";
    public static final String EDIT_ACT_SCENARIO = "UPDATE act_scenario SET priority = ?, action_status = ?::action_status,param_id = ? " +
            "WHERE id = ?";
    public static final String FIND_BY_TITLE_LIKE = "SELECT s.id as s_id, s.action_id as s_action_id, s.test_scenario_id as s_test_scenario_id," +
            " s.priority as s_priority, s.action_status as s_action_status, s.param_id as s_param_id, a.title as a_title," +
            "a.type as a_type , p.type as p_type , p.value as p_value " +
            "FROM act_scenario s INNER JOIN action a ON a.id = s.action_id " +
            "INNER JOIN parameters p ON s.param_id = p.id " +
            "WHERE a.title LIKE CONCAT('%',?,'%') ORDER BY s.priority LIMIT ? OFFSET ?";
    public static final String FIND_ALL_BY_TITLE_LIKE = "SELECT s.id as s_id, s.action_id as s_action_id, s.test_scenario_id as s_test_scenario_id," +
            " s.priority as s_priority, s.action_status as s_action_status, s.param_id as s_param_id, a.title as a_title," +
            " a.type as a_type , p.type as p_type , p.value as p_value " +
            "FROM act_scenario s INNER JOIN action a ON a.id = s.action_id " +
            "INNER JOIN parameters p ON s.param_id = p.id " +
            "WHERE a.title LIKE CONCAT('%',?,'%')";
    public static final String FIND_ALL_BY_TITLE_IN_TEST_SCENARIO = "SELECT s.id as s_id, s.action_id as s_action_id, s.test_scenario_id as s_test_scenario_id, s.priority as s_priority, s.action_status as s_action_status, s.param_id as s_param_id, a.title as a_title, a.type as a_type , p.type as p_type , p.value as p_value " +
            "FROM act_scenario s INNER JOIN action a ON a.id = s.action_id " +
            "INNER JOIN parameters p ON s.param_id = p.id " +
            "WHERE a.title LIKE CONCAT('%',?,'%') AND s.test_scenario_id = ?";
    public static final String FIND_BY_TITLE_IN_TEST_SCENARIO = "SELECT s.id as s_id, s.action_id as s_action_id, s.test_scenario_id as s_test_scenario_id," +
            " s.priority as s_priority, s.action_status as s_action_status, s.param_id as s_param_id, a.title as a_title," +
            " a.type as a_type , p.type as p_type , p.value as p_value " +
            "FROM act_scenario s INNER JOIN action a ON a.id = s.action_id " +
            "INNER JOIN parameters p ON s.param_id = p.id " +
            "WHERE a.title LIKE CONCAT('%',?,'%') AND s.test_scenario_id = ? ORDER BY s.priority LIMIT ? OFFSET ?";
    public static final String DELETE_ACT_SCENARIO = "DELETE FROM act_scenario WHERE id =?";
    public static final String SET_STATUS = "UPDATE act_scenario SET action_status = ?::action_status WHERE id = ?";
    public static final String FIND_BY_ID = "SELECT s.id as s_id, s.action_id as s_action_id, s.test_scenario_id as s_test_scenario_id," +
            " s.priority as s_priority, s.action_status as s_action_status, s.param_id as s_param_id, a.title as a_title," +
            " a.type as a_type, p.type as p_type , p.value as p_value " +
            "FROM act_scenario s INNER JOIN action a on a.id = s.action_id " +
            "INNER JOIN parameters p on s.param_id = p.id " +
            "WHERE s.id = ?";
    public static final String TOTAL_FIND_BY_TITLE_LIKE = "SELECT COUNT(*) " +
            "FROM act_scenario s INNER JOIN action a on a.id = s.action_id WHERE a.title LIKE CONCAT('%',?,'%')";
    public static final String TOTAL_FIND_BY_TITLE_IN_TEST_SCENARIO = "SELECT COUNT(*) FROM act_scenario s " +
            "INNER JOIN action a on a.id = s.action_id WHERE a.title LIKE CONCAT('%',?,'%') AND test_scenario_id = ?";
    public static final String STATUS_FOR_ALL_BY_ID_TEST_SCENARIO = "UPDATE act_scenario SET action_status = ?::action_status WHERE test_scenario_id = ?";
    public static final String DELETE_ALL_BY_ID_TEST_SCENARIO = "DELETE FROM act_scenario WHERE test_scenario_id = ?";
    public static final String DELETE_ALL_BY_ID_COMP_SCENARIO = "DELETE FROM act_scenario WHERE id = ANY(" +
            "SELECT s.id FROM act_scenario s INNER JOIN action a on a.id = s.action_id " +
            "INNER JOIN comp_act_prior cap on a.id = cap.action_id " +
            "INNER JOIN compound c on cap.comp_id = c.id " +
            "INNER JOIN comp_scenario cs on c.id = cs.compound_id " +
            "WHERE cs.id = ?)";
    public static final String FIND_BY_SIGNATURE = "SELECT s.id as s_id, s.action_id as s_action_id, s.test_scenario_id as s_test_scenario_id, " +
            "s.priority as s_priority, s.action_status as s_action_status, s.param_id as s_param_id , a.title as a_title, " +
            "a.type as a_type, p.type as p_type,p.value as p_value " +
            "FROM act_scenario s INNER JOIN action a on a.id = s.action_id " +
            "INNER JOIN parameters p on s.param_id = p.id WHERE s.action_id = ? AND s.test_scenario_id = ? AND s.priority = ?";

}
