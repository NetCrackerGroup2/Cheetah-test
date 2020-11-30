package com.ncedu.cheetahtest.dao.dataset;

import com.ncedu.cheetahtest.entity.dataset.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static com.ncedu.cheetahtest.dao.dataset.DataSetConsts.*;

@Repository
public class DataSetDaoImpl implements DataSetDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataSetDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public DataSet findById(int id) {
        List<DataSet> dataSets = jdbcTemplate.query(
                FIND_BY_ID,
                preparedStatement -> preparedStatement.setInt(1, id),
                new DataSetRowMapper()
        );
        if (dataSets.size() == 1) {
            return dataSets.get(0);
        } else return null;
    }

    @Override
    public List<DataSet> findByTitleLike(String title, int idTestCase, int limit, int offset) {
        return jdbcTemplate.query(
                FIND_BY_TITLE_LIKE,
                preparedStatement -> {
                    preparedStatement.setString(1, title);
                    preparedStatement.setInt(2, idTestCase);
                    preparedStatement.setInt(3, limit);
                    preparedStatement.setInt(4, offset);
                },
                new DataSetRowMapper());

    }

    @Override
    public DataSet createDataSet(DataSet dataSet) {
        jdbcTemplate.update(
                CREATE_DATA_SET,
                dataSet.getTitle(),
                dataSet.getDescription(),
                dataSet.getIdTestCase()
        );
        return this.findByTitle(dataSet.getTitle());


    }

    @Override
    public DataSet editDataSet(DataSet dataSet, int id) {
        jdbcTemplate.update(
                EDIT_DATA_SET,
                dataSet.getTitle(),
                dataSet.getDescription(),
                dataSet.getIdTestCase(),
                id
        );
        return findByTitle(dataSet.getTitle());
    }

    @Override
    public void deleteDataSet(int id) {
        jdbcTemplate.update(DELETE_DATA_SET, id);
    }

    @Override
    public int getTotalElements(int idTestCase,String title) {
        List<Integer> count = jdbcTemplate.query(
                GET_TOTAL_ELEMENTS,
                preparedStatement -> {
                    preparedStatement.setInt(1,idTestCase);
                    preparedStatement.setString(2,title);
                },
                new CountDataSetRowMapper());
        if (count.size() == 1) return count.get(0);
        else return 0;
    }

    @Override
    public DataSet findByTitle(String title) {
        List<DataSet> dataSets = jdbcTemplate.query(
                FIND_BY_TITLE,
                preparedStatement -> preparedStatement.setString(1, title),
                new DataSetRowMapper()
        );
        if (dataSets.size() == 1) {
            return dataSets.get(0);
        } else return null;
    }
}
