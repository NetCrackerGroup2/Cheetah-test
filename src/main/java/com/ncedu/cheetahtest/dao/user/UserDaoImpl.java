package com.ncedu.cheetahtest.dao.user;

import com.ncedu.cheetahtest.entity.user.User;
import com.ncedu.cheetahtest.entity.user.ResetToken;
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
    String sql = CREATE_DEVELOPER_SQL;

    jdbcTemplate.update(
        sql,
        user.getEmail(),
        user.getPass(),
        user.getName(),
        user.getRole().toString(),
        user.getStatus().toString()
    );

  }

  @Override
  public User findUserByEmail(String email) {
    String sql = FIND_USER_BY_EMAIL_SQL;

    List<User> users = jdbcTemplate.query(
        sql,
        preparedStatement -> preparedStatement.setString(1, email),
        new UserRowMapper());

    if (users.size() == 1) {
      return users.get(0);
    }

    return null;
  }

  @Override
  public User findUserByEmailAndPassword(String email, String password) {
    String sql = FIND_USER_BY_EMAIL_AND_PASSWORD_SQL;

    List<User> users = jdbcTemplate.query(
        sql,
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
    String sql = CHANGE_USER_PASSWORD_SQL;

    jdbcTemplate.execute(sql, (PreparedStatementCallback<Boolean>) preparedStatement -> {
      preparedStatement.setString(1, password);
      preparedStatement.setInt(2, resetToken.getDeveloperId());

      return preparedStatement.execute();
    });
  }

  @Override
  public User findUserByToken(String token) {
    String sql = FIND_USER_BY_TOKEN_SQL;

    List<User> users = jdbcTemplate.query(
        sql,
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
    String sql = SET_USER_LAST_REQUEST_SQL;

    jdbcTemplate.execute(sql, (PreparedStatementCallback<Boolean>) preparedStatement -> {
      preparedStatement.setTimestamp(1, new Timestamp(lastRequest.getTime()));
      preparedStatement.setString(2, email);

      return preparedStatement.execute();
    });
  }

  @Override
  public User editUser(UserDto user) {
    String sql = EDIT_USER_SQL;

    int result = jdbcTemplate.update(sql, user.getEmail(), user.getName(), user.getRole().toString(), user.getId());
    if (result == 1) {
      return findUserById(user.getId());
    }
    return null;
  }

  @Override
  public User changeUserStatus(long id, String status) {
    String sql = CHANGE_USER_STATUS_SQL;

    int result = jdbcTemplate.update(sql, status, id);
    if (result == 1) {
      return findUserById(id);
    }
    return null;
  }

  @Override
  public User findUserById(long id) {
    String sql = FIND_USER_BY_ID_SQL;

    List<User> users = jdbcTemplate.query(
        sql,
        preparedStatement -> preparedStatement.setLong(1, id),
        new UserRowMapper()
    );

    if (users.size() == 1) {
      return users.get(0);
    }
    return null;
  }
}
