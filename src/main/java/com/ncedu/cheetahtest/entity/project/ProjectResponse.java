package com.ncedu.cheetahtest.entity.project;

import lombok.Data;

@Data
public class ProjectResponse {
    String message;

    public ProjectResponse(String message) {
        this.message = message;
    }
}