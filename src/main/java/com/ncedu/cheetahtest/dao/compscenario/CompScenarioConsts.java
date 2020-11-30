package com.ncedu.cheetahtest.dao.compscenario;

public class CompScenarioConsts {
    public static final String CREATE_COMP_SCENARIO = "INSERT INTO comp_scenario (compound_id, test_scenario_id, priority, comp_status) " +
            "VALUES (?,?,?,?::compound_status)";
    public static final String EDIT_COMP_SCENARIO =  "UPDATE comp_scenario SET compound_id = ?,test_scenario_id = ? , priority = ?, comp_status = ?::compound_status " +
            "WHERE  id = ?";
    public static final String DELETE_COMP_SCENARIO = "DELETE FROM comp_scenario WHERE id = ?";
    public static final String FIND_BY_TITLE_LIKE = "SELECT cs.id as cs_id, cs.compound_id as cs_compound_id, cs.test_scenario_id as cs_test_scenario_id, cs.priority as cs_priority, cs.comp_status as cs_comp_status, c.title as c_title " +
            "FROM comp_scenario cs INNER JOIN compound c on c.id = cs.compound_id " +
            "WHERE c.title LIKE CONCAT('%',?,'%') ORDER BY c.title LIMIT ? OFFSET ?";
    public static final String FIND_BY_TITLE_IN_TEST_SCENARIO = "SELECT cs.id as cs_id, cs.compound_id as cs_compound_id, cs.test_scenario_id as cs_test_scenario_id, cs.priority as cs_priority, cs.comp_status as cs_comp_status , c.title as c_title " +
            "FROM comp_scenario cs INNER JOIN compound c on c.id = cs.compound_id " +
            "WHERE c.title LIKE CONCAT('%',?,'%') AND cs.test_scenario_id = ? ORDER BY cs.priority LIMIT ? OFFSET ?";
    public static final String FIND_ALL_BY_TITLE_LIKE = "SELECT cs.id as cs_id, cs.compound_id as cs_compound_id, cs.test_scenario_id as cs_test_scenario_id, cs.priority as cs_priority, cs.comp_status as cs_comp_status , c.title as c_title " +
            "FROM comp_scenario cs INNER JOIN compound c on c.id = cs.compound_id " +
            "WHERE c.title LIKE CONCAT('%',?,'%') ORDER BY c.title ";
    public static final String FIND_ALL_BY_TITLE_IN_TEST_SCENARIO = "SELECT cs.id as cs_id, cs.compound_id as cs_compound_id, cs.test_scenario_id as cs_test_scenario_id, cs.priority as cs_priority, cs.comp_status as cs_comp_status, c.title as c_title " +
            "FROM comp_scenario cs INNER JOIN compound c on c.id = cs.compound_id " +
            "WHERE c.title LIKE CONCAT('%',?,'%') AND cs.test_scenario_id = ? ORDER BY cs.priority ";
    public static final String TOTAL_FIND_BY_TITLE_LIKE  = "SELECT COUNT(*) FROM comp_scenario cs INNER JOIN compound c on c.id = cs.compound_id " +
            "WHERE c.title LIKE CONCAT('%',?,'%') ";
    public static final String TOTAL_FIND_BY_TITLE_IN_TEST_SCENARIO = "SELECT COUNT(*) FROM comp_scenario cs INNER JOIN compound c on c.id = cs.compound_id " +
            "WHERE c.title LIKE CONCAT('%',?,'%') AND cs.test_scenario_id = ? ";
    public static final String SET_STATUS_FOR_ALL_BY_ID_TEST_SCENARIO = "UPDATE comp_scenario SET comp_status = ?::action_status WHERE test_scenario_id = ?";
    public static final String GET_ALL_ACTION_SCENARIO_IN_COMP  ="SELECT s.id, s.action_id, s.test_scenario_id, s.priority, s.action_status, s.param_id ,a.title,a.type " +
            "FROM act_scenario s INNER JOIN action a on a.id = s.action_id INNER JOIN comp_act_prior cap on a.id = cap.action_id " +
            "INNER JOIN compound c on cap.comp_id = c.id INNER JOIN comp_scenario cs on c.id = cs.compound_id " +
            "WHERE cs.id = ? ORDER BY cap.priority";
    public static final String FIND_BY_ID = "SELECT cs.id as cs_id, cs.compound_id as cs_compound_id, cs.test_scenario_id as cs_test_scenario_id, cs.priority as cs_priority, cs.comp_status as cs_comp_status, c.title as c_title " +
            "FROM comp_scenario cs INNER JOIN compound c on c.id = cs.compound_id " +
            "WHERE cs.id = ?";
    public static final String FIND_BY_SIGNATURE = "SELECT cs.id as cs_id, cs.compound_id as cs_compound_id, cs.test_scenario_id as cs_test_scenario_id, cs.priority as cs_priority, cs.comp_status as cs_comp_status, c.title as c_title " +
            "FROM comp_scenario cs INNER JOIN compound c on c.id = cs.compound_id " +
            "WHERE cs.compound_id = ? AND cs.test_scenario_id  =? AND cs.priority = ?";
    public static final String DELETE_ALL_BY_TEST_SCENARIO = "DELETE FROM comp_scenario WHERE test_scenario_id = ?";
}
