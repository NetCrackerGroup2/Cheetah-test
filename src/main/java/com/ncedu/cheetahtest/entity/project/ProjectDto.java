package com.ncedu.cheetahtest.entity.project;

import lombok.Data;

import java.util.List;

@Data
public class ProjectDto {
    private Project project;
        private List<Integer> watcherIds;

    public ProjectDto(Project project, List<Integer> watcherIds) {
        this.project = project;
        this.watcherIds = watcherIds;
    }
}
