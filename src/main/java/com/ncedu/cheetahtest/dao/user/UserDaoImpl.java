package com.ncedu.cheetahtest.dao.user;

import com.ncedu.cheetahtest.entity.user.ResetToken;
import com.ncedu.cheetahtest.entity.user.User;
import com.ncedu.cheetahtest.entity.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static com.ncedu.cheetahtest.dao.user.UserConsts.*;

@Repository
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createDeveloper(User user) {

        jdbcTemplate.update(
                CREATE_DEVELOPER_SQL,
                user.getEmail(),
                user.getPass(),
                user.getName(),
                user.getRole().toString(),
                user.getStatus().toString()
        );

    }

    @Override
    public User findUserByEmail(String email) {
        List<User> users = jdbcTemplate.query(
                FIND_USER_BY_EMAIL_SQL,
                preparedStatement -> preparedStatement.setString(1, email),
                new UserRowMapper());
        if (users.size() == 1) {
            return users.get(0);
        }

        return null;
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {

        List<User> users = jdbcTemplate.query(
                FIND_USER_BY_EMAIL_AND_PASSWORD_SQL,
                preparedStatement -> {
                    preparedStatement.setString(1, email);
                    preparedStatement.setString(2, password);
                },
                new UserRowMapper());

        if (users.size() == 1) {
            return users.get(0);
        }

        return null;
    }

    @Override
    public void changeUserPassword(ResetToken resetToken, String password) {

        jdbcTemplate.execute(CHANGE_USER_PASSWORD_SQL,
                (PreparedStatementCallback<Boolean>) preparedStatement -> {
                    preparedStatement.setString(1, password);
                    preparedStatement.setInt(2, resetToken.getDeveloperId());

                    return preparedStatement.execute();
                });
    }

    @Override
    public User findUserByToken(String token) {

        List<User> users = jdbcTemplate.query(
                FIND_USER_BY_TOKEN_SQL,
                preparedStatement -> {
                    preparedStatement.setString(1, token);
                },
                new UserRowMapper()
        );

        if (users.size() == 1) {
            return users.get(0);
        }

        return null;
    }

    @Override
    public void setUserLastRequest(String email, Date lastRequest) {

        jdbcTemplate.execute(SET_USER_LAST_REQUEST_SQL,
                (PreparedStatementCallback<Boolean>) preparedStatement -> {
                    preparedStatement.setTimestamp(1, new Timestamp(lastRequest.getTime()));
                    preparedStatement.setString(2, email);

                    return preparedStatement.execute();
                });
    }

    @Override
    public User editUser(UserDto user) {

        int result = jdbcTemplate.update(EDIT_USER_SQL,
                user.getEmail(), user.getName(),
                user.getRole().toString(), user.getId());
        if (result == 1) {
            return findUserById(user.getId());
        }
        return null;
    }

    @Override
    public User changeUserStatus(long id, String status) {

        int result = jdbcTemplate.update(CHANGE_USER_STATUS_SQL, status, id);
        if (result == 1) {
            return findUserById(id);
        }
        return null;
    }

    @Override
    public User findUserById(long id) {

        List<User> users = jdbcTemplate.query(
                FIND_USER_BY_ID_SQL,
                preparedStatement -> preparedStatement.setLong(1, id),
                new UserRowMapper()
        );

        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }
}
