package com.ncedu.cheetahtest.dao.user;

import com.ncedu.cheetahtest.dao.genericdao.AbstractDaoImpl;
import com.ncedu.cheetahtest.entity.user.User;
import com.ncedu.cheetahtest.entity.user.ResetToken;
import com.ncedu.cheetahtest.entity.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ncedu.cheetahtest.dao.user.UserConsts.*;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

    private static final String[] rows =
            {"id", "email", "password", "name",
                    "role", "status", "last_request"};

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        super(new UserRowMapper(), jdbcTemplate,
                rows, "users");
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
                preparedStatement -> preparedStatement.setString(1, token),
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

    @Override
    public List<User> getAllActiveUser(){
        return jdbcTemplate.query(FIND_ALL_ACTIVE_USERS_SQL, new UserRowMapper());
    }

    @Override
    public List<User> getSearchUserByNameEmailRole(String name, String email,
                                                   String role, int size, int page) {
        final String preparateRole;
        if (role.length() == 0){
            preparateRole = "%";
        }
        else {
            preparateRole = role.toUpperCase();
        }
        return jdbcTemplate.query(FIND_USER_BY_EMAIL_NAME_ROLE_SQL,
                preparatedStatemetn -> {
                    preparatedStatemetn.setString(1, "%" + email + "%");
                    preparatedStatemetn.setString(2, "%" + name + "%");
                    preparatedStatemetn.setString(3, preparateRole);
                    preparatedStatemetn.setInt(4, size);
                    preparatedStatemetn.setInt(5, (page - 1) * size);
                },
                new UserRowMapper());
    }

    @Override
    public Integer getCountSearchUserByNameEmailRole(String name, String email,
                                              String role){
        final String preparateRole;
        if (role.length() == 0){
            preparateRole = "%";
        }
        else {
            preparateRole = role.toUpperCase();
        }
        return jdbcTemplate.queryForObject(COUNT_USER_BY_EMAIL_NAME_ROLE_SQL,
                new Object[] {"%" + email + "%", "%" + name + "%", preparateRole},
                Integer.class);
    }
    return null;
  }

  @Override
  public List<UserDto> findByEmail(String title) {
    return jdbcTemplate.query(
            FIND_BY_EMAIL,
            preparedStatement -> preparedStatement.setString(1,title),
            new UserDtoRowMapper()
    );
  }

}

