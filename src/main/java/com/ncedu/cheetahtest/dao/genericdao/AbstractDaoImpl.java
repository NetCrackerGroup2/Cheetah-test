package com.ncedu.cheetahtest.dao.genericdao;

import com.ncedu.cheetahtest.exception.general.InvalidParametersException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;
import java.util.Objects;

public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {

    protected final RowMapper<T> rowMapper;

    protected final JdbcTemplate jdbcTemplate;

    protected final String tableName;

    protected final Consts commonConsts;

    protected AbstractDaoImpl(RowMapper<T> rowMapper, JdbcTemplate jdbcTemplate,
                           String[] rows, String tableName) {
        this.rowMapper = rowMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.tableName = tableName;
        this.commonConsts = new Consts(rows, tableName);
    }

    @Override
    public List<T> getActivePaginated(int page, int size) {
        int offset = getOffset(page, size);

        return jdbcTemplate.query(
                commonConsts.getActivePaginated(),
                preparedStatement -> {
                    preparedStatement.setInt(1, size);
                    preparedStatement.setInt(2, offset);
                },
                rowMapper
        );
    }

    @Override
    public List<T> getAllPaginated(int page, int size) {

        int offset = getOffset(page, size);

        return jdbcTemplate.query(
                commonConsts.getAllPaginated(),
                preparedStatement -> {
                    preparedStatement.setInt(1, size);
                    preparedStatement.setInt(2, offset);
                },
                rowMapper
        );
    }

    @Override
    public int getAmountActiveElements() {
        Integer amountOfTestCases = DataAccessUtils.singleResult(
                jdbcTemplate.queryForList(
                        commonConsts.getAmountActive(),
                        Integer.class
                )
        );

        return Objects.requireNonNullElse(amountOfTestCases, 0);
    }

    @Override
    public int getAmountAllElements() {
        Integer amountOfTestCases = DataAccessUtils.singleResult(
                jdbcTemplate.queryForList(
                        commonConsts.getAmountAll(),
                        Integer.class
                )
        );

        return Objects.requireNonNullElse(amountOfTestCases, 0);
    }

    @Override
    public T findById(int id) {

        List<T> list = jdbcTemplate.query(
                commonConsts.getById(),
                preparedStatement -> preparedStatement.setInt(1, id),
                rowMapper
        );

        if (list.size() == 1) {
            return list.get(0);
        }

        return null;
    }

    @Override
    public int getSearchedActiveTotalElements(String title) {
        return getSingleIntElement("%" + title + "%",
                commonConsts.getAmountActiveSearched());
    }

    @Override
    public int getSearchedAllTotalElements(String title) {
        return getSingleIntElement("%" + title + "%",
                commonConsts.getAmountAllSearched());
    }

    @Override
    public List<T> findActiveByTitlePaginated(int page, int size, String title) {
        int offset = getOffset(page, size);

        return jdbcTemplate.query(
                commonConsts.getActiveSearched(),
                preparedStatement -> {
                    preparedStatement.setString(1, "%" + title + "%");
                    preparedStatement.setInt(2, size);
                    preparedStatement.setInt(3, offset);
                },
                rowMapper
        );
    }

    @Override
    public List<T> findAllByTitlePaginated(int page, int size, String title) {
        int offset = getOffset(page, size);

        return jdbcTemplate.query(
                commonConsts.getAllSearched(),
                preparedStatement -> {
                    preparedStatement.setString(1, "%" + title + "%");
                    preparedStatement.setInt(2, size);
                    preparedStatement.setInt(3, offset);
                },
                rowMapper
        );
    }

    @Override
    public int getSingleIntElement(String title, String getAmountOfAllSearchedTestCases) {
        List<Integer> amountOfTestCases = jdbcTemplate.query(
                getAmountOfAllSearchedTestCases,
                preparedStatement ->
                        preparedStatement.setString(1, title),
                (resultSet, i) -> resultSet.getInt(1)
        );

        if (amountOfTestCases.size() == 1) {
            return amountOfTestCases.get(0);
        }

        return 0;
    }

    private int getOffset(int page, int size) {
        if (page <= 0 || size < 0) {
            throw new InvalidParametersException("In " + getClass().getName());
        }

        return size * (page - 1);
    }

}