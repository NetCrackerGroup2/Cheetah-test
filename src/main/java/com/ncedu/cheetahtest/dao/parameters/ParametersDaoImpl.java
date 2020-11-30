package com.ncedu.cheetahtest.dao.parameters;


import com.ncedu.cheetahtest.entity.parameter.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static com.ncedu.cheetahtest.dao.parameters.ParametersConsts.*;

@Repository
public class ParametersDaoImpl implements ParametersDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParametersDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Parameter findById(int id) {
        List<Parameter> parameters = jdbcTemplate.query(
                FIND_BY_ID,
                preparedStatement -> preparedStatement.setInt(1, id),
                new ParametersRowMapper()
        );
        if (parameters.size() == 1) {
            return parameters.get(0);
        } else return null;
    }

    @Override
    public List<Parameter> findByTypeLike(String type, int idDataSet, int limit, int offset) {
        return jdbcTemplate.query(
                FIND_BY_TITLE_LIKE,
                preparedStatement -> {
                    preparedStatement.setString(1, type);
                    preparedStatement.setInt(2, idDataSet);
                    preparedStatement.setInt(3, limit);
                    preparedStatement.setInt(4, offset);
                },
                new ParametersRowMapper()
        );
    }

    @Override
    public Parameter createParameter(Parameter parameter) {
        jdbcTemplate.update(
                CREATE_PARAMETERS,
                parameter.getIdDataSet(),
                parameter.getType(),
                parameter.getValue()
        );
        return this.findByTypeAndIdDataSet(parameter.getType(), parameter.getIdDataSet());
    }

    @Override
    public Parameter editParameter(Parameter parameter, int id) {
        jdbcTemplate.update(
                EDIT_PARAMETER,
                parameter.getIdDataSet(),
                parameter.getType(),
                parameter.getValue(),
                id
        );
        return findByTypeAndIdDataSet(parameter.getType(), parameter.getIdDataSet());
    }

    @Override
    public void deleteParameter(int id) {
        jdbcTemplate.update(DELETE_PARAMETER, id);

    }

    @Override
    public int getTotalElements(int idDataSet, String type) {
        List<Integer> count = jdbcTemplate.query(
                GET_TOTAL_ELEMENTS,
                preparedStatement -> {
                    preparedStatement.setInt(1, idDataSet);
                    preparedStatement.setString(2, type);
                },
                new CountParametersRowMapper());
        if (count.size() == 1) return count.get(0);
        else return 0;
    }

    @Override
    public Parameter findByTypeAndIdDataSet(String type, int idDataSet) {
        List<Parameter> parameters = jdbcTemplate.query(
                FIND_BY_TITLE_AND_ID_DATA_SET,
                preparedStatement -> {
                    preparedStatement.setString(1, type);
                    preparedStatement.setInt(2, idDataSet);
                },
                new ParametersRowMapper()
        );
        if (parameters.size() == 1) {
            return parameters.get(0);
        } else return null;
    }

    @Override
    public void deleteByIdDataSet(int idDataSet) {
        String sql = "DELETE FROM parameters WHERE data_set_id = ?";
        jdbcTemplate.update(
                sql,
                idDataSet
        );
    }
}
