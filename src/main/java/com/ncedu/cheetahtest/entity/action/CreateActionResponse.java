package com.ncedu.cheetahtest.entity.action;

import lombok.Data;

@Data
public class CreateActionResponse {
    private String status;
    public CreateActionResponse(String status){
        this.status = status;
    }
}
