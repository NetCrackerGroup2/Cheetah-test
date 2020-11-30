package com.ncedu.cheetahtest.service.project;

import com.ncedu.cheetahtest.entity.project.Project;
import com.ncedu.cheetahtest.entity.project.ProjectDto;
import com.ncedu.cheetahtest.entity.project.ResponseProjectPaginated;

import java.util.List;

public interface ProjectService {
    void createNewProject(ProjectDto newProject);
    ResponseProjectPaginated getAllProjects(int offset, int size);
    List<Project> getAllArchievedProjects();
    void setArchievedStatus(int id);
    Project getProjectById(int id);
    ResponseProjectPaginated getProjectsPaginatedByTitle(int page, int size, String title);
}
