package com.ncedu.cheetahtest.dao.compound;

import com.ncedu.cheetahtest.entity.compound.Compound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static com.ncedu.cheetahtest.dao.compound.CompoundConsts.*;

@Repository
public class CompoundDaoImpl implements CompoundDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CompoundDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Compound createCompound(Compound compound) {
        jdbcTemplate.update(
                CREATE_COMPOUND,
                compound.getTitle(),
                compound.getDescription()
        );
        return this.findByTitle(compound.getTitle());
    }

    private Compound findCompoundById(int id) {
        List<Compound> compounds = jdbcTemplate.query(
                FIND_COMPOUND_BY_ID,
                preparedStatement -> preparedStatement.setInt(1, id),
                new CompoundRowMapper());
        if (compounds.size() == 1) {
            return compounds.get(0);
        } else {
            return null;
        }

    }

    @Override
    public List<Compound> selectCompoundsByTitleLike(String title, int limit, int offset) {
        return jdbcTemplate.query(
                SELECT_COMPOUND_BY_TITLE_LIKE,
                preparedStatement -> {
                    preparedStatement.setString(1, title);
                    preparedStatement.setInt(2, limit);
                    preparedStatement.setInt(3, offset);
                },
                new CompoundRowMapper()
        );

    }

    @Override
    public Compound editCompound(Compound compound, int id) {
        jdbcTemplate.update(
                EDIT_COMPOUND,
                compound.getTitle(),
                compound.getDescription(),
                id
        );

        return findCompoundById(id);
    }

    @Override
    public void removeCompoundById(int id) {
        jdbcTemplate.update(
                REMOVE_COMPOUND_BY_ID,
                id
        );
    }

    @Override
    public Compound findByTitle(String title) {
        List<Compound> compounds = jdbcTemplate.query(
                FIND_BY_TITLE,
                preparedStatement -> preparedStatement.setString(1, title),
                new CompoundRowMapper()
        );
        if (compounds.size() == 1) {
            return compounds.get(0);
        } else {
            return null;
        }
    }

    @Override
    public int getTotalCompByTitle(String title) {
        List<Integer> count = jdbcTemplate.query(
                GET_TOTAL_COMP_BY_TITLE,
                preparedStatement -> preparedStatement.setString(1, title),
                new CountCompoundRowMapper()
        );
        if (count.size() == 1) {
            return count.get(0);
        } else {
            return 0;
        }
    }
}
