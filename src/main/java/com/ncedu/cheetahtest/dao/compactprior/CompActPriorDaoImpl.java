package com.ncedu.cheetahtest.dao.compactprior;

import com.ncedu.cheetahtest.entity.compactprior.CompActPrior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import static com.ncedu.cheetahtest.dao.compactprior.CompActPriorConsts.*;

@Repository
public class CompActPriorDaoImpl implements CompActPriorDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired

    public CompActPriorDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createCompActPrior(CompActPrior compActPrior) {
        jdbcTemplate.update(
                CREATE_COMP_ACT_PRIOR,
                compActPrior.getCompId(),
                compActPrior.getActId(),
                compActPrior.getPriority()
        );
    }

    @Override
    public void deleteByIdCompound(int idCopmound) {
        jdbcTemplate.update(DELETE_BY_ID_COMPOUND,
                idCopmound);
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }
}
