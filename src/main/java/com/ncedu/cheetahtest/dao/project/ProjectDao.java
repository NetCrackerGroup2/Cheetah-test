package com.ncedu.cheetahtest.dao.project;

import com.ncedu.cheetahtest.entity.project.Project;
import com.ncedu.cheetahtest.entity.project.ProjectDto;

import java.util.List;

public interface ProjectDao {

    List<Project> getAllPaginated(int offset, int size);

    List<Project> findAllByTitlePaginated(int offset, int size, String title);

    int getAmountAllElements();

    void createProject(ProjectDto project);

    void setArchievedStatus(int id);

    int getSearchedAllTotalElements(String title);

    List<Project> getAllArchievedProjects();

    Project findByProjectId(int id);

    int getAmountActiveElements();

    List<Project> getActivePaginated(int page, int size);

    int getSearchedActiveTotalElements(String title);

    List<Project> findActiveByTitlePaginated(int page, int size, String title);

}
