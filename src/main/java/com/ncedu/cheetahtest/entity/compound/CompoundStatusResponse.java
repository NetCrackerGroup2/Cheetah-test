package com.ncedu.cheetahtest.entity.compound;

import lombok.Data;

@Data
public class CompoundStatusResponse {
    private String status;

    public CompoundStatusResponse(String status) {
        this.status = status;
    }
}
