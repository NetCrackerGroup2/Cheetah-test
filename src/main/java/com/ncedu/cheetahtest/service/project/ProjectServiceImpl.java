package com.ncedu.cheetahtest.service.project;

import com.ncedu.cheetahtest.dao.project.ProjectDao;
import com.ncedu.cheetahtest.entity.project.Project;
import com.ncedu.cheetahtest.entity.project.ProjectDto;
import com.ncedu.cheetahtest.entity.project.ResponseProjectPaginated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectDao projectDao;

    @Autowired
    public ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public void createNewProject(ProjectDto newProjectDto) {
        projectDao.createProject(newProjectDto);
    }

    @Override
    public ResponseProjectPaginated getAllProjects(int page, int size) {
        int totalElements = projectDao.getAmountAllElements();
        List<Project> projects = projectDao.getAllPaginated(page, size);

        return new ResponseProjectPaginated(projects, totalElements);
    }

    @Override
    public List<Project> getAllArchievedProjects() {
        return projectDao.getAllArchievedProjects();
    }

    @Override
    public void setArchievedStatus(int id) {
        projectDao.setArchievedStatus(id);
    }

    @Override
    public Project getProjectById(int id) {
        return projectDao.findByProjectId(id);
    }

    @Override
    public ResponseProjectPaginated getProjectsPaginatedByTitle(int page, int size, String title) {
        int totalElements = projectDao.getSearchedAllTotalElements(title);
        List<Project> projects = projectDao.findAllByTitlePaginated(page, size, title);

        return new ResponseProjectPaginated(projects, totalElements);
    }

    @Override
    public ResponseProjectPaginated getActiveProjects(int page, int size) {
        int totalElements = projectDao.getAmountActiveElements();
        List<Project> projects = projectDao.getActivePaginated(page, size);

        return new ResponseProjectPaginated(projects, totalElements);
    }

    @Override
    public ResponseProjectPaginated getActiveProjectsByTitle(int page, int size, String title) {
        int totalElements = projectDao.getSearchedActiveTotalElements(title);
        List<Project> projects = projectDao.findActiveByTitlePaginated(page, size, title);

        return new ResponseProjectPaginated(projects, totalElements);
    }
}
