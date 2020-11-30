package com.ncedu.cheetahtest.entity.user;

import lombok.Data;

import java.util.Date;

@Data
public class ResetToken {
    private static final int EXPIRATION = 3600 * 24 * 1000;

    private int id;

    private String token;

    private int developerId;

    private Date expiryDate;

    public ResetToken() {
    }

    public ResetToken(String token, int developerId, Date expiryDate) {
        this.token = token;
        this.developerId = developerId;
        this.expiryDate = expiryDate;
    }

    public static int getEXPIRATION() {
        return EXPIRATION;
    }

}
