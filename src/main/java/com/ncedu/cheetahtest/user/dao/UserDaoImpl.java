package com.ncedu.cheetahtest.user.dao;

import com.ncedu.cheetahtest.user.entity.User;
import com.ncedu.cheetahtest.user.entity.ResetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createDeveloper(User user) {
        String sql = "INSERT INTO users (email, password, name, role, status) VALUES (?,?,?,?::user_role,?::user_status)";

        jdbcTemplate.update(
                sql,
                user.getEmail(),
                user.getPass(),
                user.getName(),
                user.getRole(),
                user.getStatus()
        );

    }

    @Override
    public User findUserByEmail(String email) {
        String sql = "SELECT id, email, password, name, role, status, reset_token_id FROM users WHERE email = ?";

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
        String sql = "SELECT id, email, password, name, role, status, reset_token_id FROM users WHERE email = ? AND password = ?";

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
    public void saveToken(ResetToken myToken) {

        String sql = "UPDATE reset_token SET token = ?, expiry_date = ? WHERE user_id = ?";

        jdbcTemplate.execute(sql, (PreparedStatementCallback<Boolean>) preparedStatement -> {
            preparedStatement.setString(1, myToken.getToken());
            preparedStatement.setTimestamp(2, new Timestamp(new Date().getTime() + ResetToken.getEXPIRATION()));
            preparedStatement.setInt(3, myToken.getDeveloperId());

            return preparedStatement.execute();
        });
    }

    @Override
    public ResetToken findResetTokenByToken(String token) {
        String sql = "SELECT id, token, expiry_date, user_id FROM reset_token WHERE token = ?";

        List<ResetToken> resetTokens = jdbcTemplate.query(
                sql,
                preparedStatement -> preparedStatement.setString(1, token),
                new ResetTokenRowMapper());

        if (resetTokens.size() == 1) {
            return resetTokens.get(0);
        }

        return null;
    }

    @Override
    public void changeUserPassword(ResetToken resetToken, String password) {
        String sql = "UPDATE users SET password = ? WHERE id = ?";

        jdbcTemplate.execute(sql, (PreparedStatementCallback<Boolean>) preparedStatement -> {
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, resetToken.getDeveloperId());

            return preparedStatement.execute();
        });
    }

    @Override
    public User findUserByToken(String token) {
        String sql = "SELECT id, email, password, name, role, status, reset_token_id FROM users WHERE id IN (SELECT user_id FROM reset_token WHERE token = ?)";

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
    public ResetToken findResetTokenByUserId(int id) {
        String sql = "SELECT id, token, expiry_date, user_id FROM reset_token WHERE user_id = ?";

        List<ResetToken> resetTokens = jdbcTemplate.query(sql,
                                        preparedStatement -> preparedStatement.setInt(1, id),
                                        new ResetTokenRowMapper());

        if (resetTokens.size() == 1) {
            return resetTokens.get(0);
        }

        return null;
    }

    @Override
    public void createToken(ResetToken myToken) {
        String sql = "INSERT INTO reset_token (token, expiry_date, user_id) VALUES (?,?,?)";

        jdbcTemplate.execute(sql, (PreparedStatementCallback<Boolean>) preparedStatement -> {
            preparedStatement.setString(1, myToken.getToken());
            preparedStatement.setTimestamp(2, new Timestamp(new Date().getTime() + ResetToken.getEXPIRATION()));
            preparedStatement.setInt(3, myToken.getDeveloperId());

            return preparedStatement.execute();
        });
    }

    @Override
    public void makeTokenExpired(ResetToken resetToken) {
        String sql = "UPDATE reset_token SET expiry_date = current_timestamp WHERE token = ?";

        jdbcTemplate.execute(sql, (PreparedStatementCallback<Boolean>) preparedStatement -> {
            preparedStatement.setString(1, resetToken.getToken());

            return preparedStatement.execute();
        });
    }
}
