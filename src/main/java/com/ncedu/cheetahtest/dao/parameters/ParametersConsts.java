package com.ncedu.cheetahtest.dao.parameters;

public class ParametersConsts {
    public static final String FIND_BY_ID = "SELECT id, data_set_id, type, value " +
            "FROM parameters WHERE id = ?";
    public static final String FIND_BY_TITLE_LIKE = "SELECT id, data_set_id, type, value " +
            "FROM parameters WHERE type LIKE CONCAT('%',?,'%') AND data_set_id=? " +
            "ORDER BY type limit ? offset ?";
    public static final String CREATE_PARAMETERS = "INSERT INTO parameters(data_set_id, type, value) VALUES (?,?,?)";
    public static final String EDIT_PARAMETER = "UPDATE parameters SET data_set_id = ?, type = ?, value = ? " +
            "WHERE id = ?";
    public static final String DELETE_PARAMETER = "DELETE FROM parameters WHERE id = ?";
    public static final String GET_TOTAL_ELEMENTS = "SELECT count(*) FROM parameters WHERE data_set_id= ? AND type LIKE concat('%',?,'%')";
    public static final String FIND_BY_TITLE_AND_ID_DATA_SET = "SELECT id, data_set_id, type, value  FROM parameters " +
            "WHERE type = ? AND data_set_id = ?";

}
