package com.ncedu.cheetahtest.entity.project;

import lombok.Data;

import java.util.List;

@Data
public class ResponseProjectPaginated {
    private List<Project> projects;
    private int totalElements;

    public ResponseProjectPaginated(List<Project> projects, int totalElements) {
        this.projects = projects;
        this.totalElements = totalElements;
    }
}
