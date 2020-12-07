package com.ncedu.cheetahtest.dao.testscenario;

public class TestScenarioConsts {

    private TestScenarioConsts() {
    }

    public static final String CREATE_TEST_SCENARIO = "INSERT INTO test_scenario (title, description, status, test_case_id) VALUES (?,?,?::test_scenario_status,?)";

    public static final String EDIT_TEST_SCENARIO = "UPDATE test_scenario SET title = ? , description = ? , status = ?::test_scenario_status , test_case_id = ? " +
            "WHERE id = ?";
    public static final String DELETE_TEST_SCENARIO = "DELETE FROM test_scenario WHERE id = ?";

    public static final String GET_TOTAL_ELEMENTS_FROM_SEARCH = "SELECT count(*) FROM test_scenario WHERE test_case_id = ? AND title LIKE concat('%',?,'%')";

    public static final String FIND_BY_TITLE = "SELECT id,title,description,status,test_case_id FROM test_scenario " +
            "WHERE title = ?";

    public static final String FIND_BY_TITLE_LIKE = "SELECT id,title,description,status,test_case_id " +
            "FROM test_scenario WHERE title LIKE CONCAT('%',?,'%') AND test_case_id=? " +
            "ORDER BY title limit ? offset ?";

    public static final String DEACTIVATE_TEST_SCENARIO = "UPDATE test_scenario SET status = 'INACTIVE'::test_scenario_status WHERE id = ?";

    public static final String FIND_SCENARIOS_FROM_TEST_CASE =
            "SELECT id,title,description,status,test_case_id FROM test_scenario" +
                    " WHERE test_case_id=? ORDER BY title limit ? offset ?";

    public static final String GET_SCENARIOS_FROM_TEST_CASE_AMOUNT = "SELECT count(*) FROM test_scenario " +
            "WHERE test_case_id = ? ";

    public static final String FIND_TEST_SCENARIO_BY_TITLE_EXCEPT_CURRENT =
            "SELECT id,title,description,status,test_case_id FROM test_scenario " +
                    " WHERE title = ? AND id <> ? LIMIT 1";

    public static final String GET_ALL_ITEMS_AMOUNT = "SELECT COUNT(*)" +
            "FROM ( (SELECT c_sc.id id_item FROM comp_scenario c_sc INNER JOIN compound c ON c_sc.compound_id = c.id" +
            " WHERE c_sc.test_scenario_id = ?) UNION ALL ( SELECT a_sc.id id_item" +
            " FROM act_scenario a_sc INNER JOIN action a ON a_sc.action_id = a.id WHERE a_sc.test_scenario_id = ?) ) as count";

    public static final String GET_ALL_ITEMS = "( SELECT" +
            " c_sc.id id_item,c_sc.priority priority,c.title title,c.description description,'COMPOUND' as kind" +
            " FROM comp_scenario c_sc INNER JOIN compound c ON c_sc.compound_id = c.id WHERE c_sc.test_scenario_id = ? )" +
            " UNION ALL ( SELECT a_sc.id id_item, a_sc.priority priority, a.title title, a.description description,  'ACTION' as kind" +
            " FROM act_scenario a_sc INNER JOIN action a ON a_sc.action_id = a.id WHERE a_sc.test_scenario_id = ? )" +
            " ORDER BY priority LIMIT ? OFFSET ?;";

}
