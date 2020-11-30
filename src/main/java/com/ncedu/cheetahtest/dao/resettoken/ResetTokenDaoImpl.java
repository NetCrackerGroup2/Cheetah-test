package com.ncedu.cheetahtest.dao.resettoken;

import com.ncedu.cheetahtest.entity.user.ResetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class ResetTokenDaoImpl implements ResetTokenDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ResetTokenDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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

}
