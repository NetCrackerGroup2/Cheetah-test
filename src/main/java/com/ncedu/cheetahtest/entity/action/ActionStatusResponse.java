package com.ncedu.cheetahtest.entity.action;

import lombok.Data;

@Data
public class ActionStatusResponse {
    private String status;
    public ActionStatusResponse(String status){
        this.status = status;
    }
}
