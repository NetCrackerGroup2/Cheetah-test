package com.ncedu.cheetahtest.dao.dashboard;

import com.ncedu.cheetahtest.dao.genericdao.CountRowMapper;
import com.ncedu.cheetahtest.entity.dashboard.UserActivityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ncedu.cheetahtest.dao.dashboard.DashboardConsts.*;


@Repository
@RequiredArgsConstructor
public class DashboardDaoImpl implements  DashboardDao{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<UserActivityDTO> getActiveUsersForAdminPerDays(String days) {
        return jdbcTemplate.query(
                GET_ALL_USER_ACTIVITY_SQL,
                preparedStatement -> preparedStatement.setString(1, "'"+days+" days'"),
                new UserActivityMapper()
        );
    }

    @Override
    public List<UserActivityDTO> getActiveUsersForManagerPerDays(String days) {
        return jdbcTemplate.query(
                GET_USER_ACTIVITY_FOR_MANAGER_SQL,
                preparedStatement -> preparedStatement.setString(1,"'"+days+" days'"),
                new UserActivityMapper()
        );
    }

    @Override
    public int getCountActiveUserByRole(String role) {
        List<Integer> count = jdbcTemplate.query(COUNT_ACTIVE_USER_BY_ROLE_SQL,
                preparedStatement -> preparedStatement.setString(1, role),
                new CountRowMapper());
        if (count.size() == 1) return count.get(0);
        else return 0;
    }
}
