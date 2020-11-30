package com.ncedu.cheetahtest.dao.resettoken;

import com.ncedu.cheetahtest.entity.user.ResetToken;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetTokenRowMapper implements RowMapper<ResetToken> {
    public static final String ID = "id";
    public static final String TOKEN = "token";
    public static final String EXPIRY_DATE = "expiry_date";
    public static final String USER_ID = "user_id";

    @Override
    public ResetToken mapRow(ResultSet rs, int i) throws SQLException {
        ResetToken resetToken = new ResetToken();
        resetToken.setId(rs.getInt(ID));
        resetToken.setToken(rs.getString(TOKEN));
        resetToken.setDeveloperId(rs.getInt(USER_ID));
        resetToken.setExpiryDate(rs.getTimestamp(EXPIRY_DATE));

        return resetToken;
    }
}
