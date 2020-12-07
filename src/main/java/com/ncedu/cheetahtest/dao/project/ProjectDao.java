package com.ncedu.cheetahtest.dao.project;

import com.ncedu.cheetahtest.entity.project.Project;
import com.ncedu.cheetahtest.entity.project.ProjectDto;

import java.util.List;

public interface ProjectDao {

    void createProject(ProjectDto project);

    void setArchievedStatus(int id);

    List<Project> getAllArchievedProjects();

    Project findByProjectId(int id);

    void updateProjectById(int id, Project project);
}
