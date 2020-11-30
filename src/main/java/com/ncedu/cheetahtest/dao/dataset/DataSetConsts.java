package com.ncedu.cheetahtest.dao.dataset;

public class DataSetConsts {
    public static final String FIND_BY_ID = "SELECT id,title,description,test_case_id " +
            "FROM data_set WHERE id = ?";
    public static final String FIND_BY_TITLE_LIKE = "SELECT id,title,description,test_case_id " +
            "FROM data_set WHERE title LIKE CONCAT('%',?,'%') AND test_case_id=? " +
            "ORDER BY title limit ? offset ?";
    public static final String CREATE_DATA_SET = "INSERT INTO data_set(title, description, test_case_id) VALUES (?,?,?)";
    public static final String EDIT_DATA_SET = "UPDATE data_set SET title = ? , description = ? ,test_case_id = ? " +
            "WHERE id = ?";
    public static final String DELETE_DATA_SET = "DELETE FROM data_set WHERE id = ?";
    public static final String GET_TOTAL_ELEMENTS = "SELECT count(*) FROM data_set WHERE test_case_id = ? AND title LIKE concat('%',?,'%')";
    public static final String FIND_BY_TITLE = "SELECT id,title,description,test_case_id FROM data_set " +
            "WHERE title = ?";

}
