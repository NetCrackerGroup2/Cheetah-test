package com.ncedu.cheetahtest.dao.compscenario;

import com.ncedu.cheetahtest.entity.actscenario.ActStatus;
import com.ncedu.cheetahtest.entity.compscenario.CompScenario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompScenarioRowMapper implements RowMapper<CompScenario> {
    public static String ID = "cs_id";
    public static String COMPID = "cs_compound_id";
    public static String TESTSCENARIO = "cs_test_scenario_id";
    public static String PRIORITY = "cs_priority";
    public static String STATUS = "cs_comp_status";
    public static String TITLE = "c_title";

    @Override
    public CompScenario mapRow(ResultSet resultSet, int i) throws SQLException {
        CompScenario compScenario = new CompScenario();
        compScenario.setId(resultSet.getInt(ID));
        compScenario.setIdCompound(resultSet.getInt(COMPID));
        compScenario.setIdTestScenario(resultSet.getInt(TESTSCENARIO));
        compScenario.setPriority(resultSet.getInt(PRIORITY));
        compScenario.setStatus(ActStatus.valueOf(resultSet.getString(STATUS)));
        compScenario.setTitle(resultSet.getString(TITLE));
        return compScenario;

    }
}
