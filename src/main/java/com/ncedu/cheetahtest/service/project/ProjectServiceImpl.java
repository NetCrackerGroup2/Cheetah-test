package com.ncedu.cheetahtest.service.project;

import com.ncedu.cheetahtest.dao.genericdao.AbstractActiveDao;
import com.ncedu.cheetahtest.dao.project.ProjectDao;
import com.ncedu.cheetahtest.entity.project.Project;
import com.ncedu.cheetahtest.entity.project.ProjectDto;
import com.ncedu.cheetahtest.entity.project.ResponseProjectPaginated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectDao projectDao;
    private final AbstractActiveDao<Project> projectGenDao;

    @Override
    public void createNewProject(ProjectDto newProjectDto) {
        projectDao.createProject(newProjectDto);
    }

    @Override
    public ResponseProjectPaginated getAllProjects(int page, int size) {
        int totalElements = projectGenDao.getAmountAllElements();
        List<Project> projects = projectGenDao.getAllPaginated(page, size);

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
        int totalElements = projectGenDao.getSearchedAllTotalElements(title);
        List<Project> projects = projectGenDao.findAllByTitlePaginated(page, size, title);

        return new ResponseProjectPaginated(projects, totalElements);
    }

    @Override
    public ResponseProjectPaginated getActiveProjects(int page, int size) {
        int totalElements = projectGenDao.getAmountActiveElements();
        List<Project> projects = projectGenDao.getActivePaginated(page, size);

        return new ResponseProjectPaginated(projects, totalElements);
    }

    @Override
    public ResponseProjectPaginated getActiveProjectsByTitle(int page, int size, String title) {
        int totalElements = projectGenDao.getSearchedActiveTotalElements(title);
        List<Project> projects = projectGenDao.findActiveByTitlePaginated(page, size, title);

        return new ResponseProjectPaginated(projects, totalElements);
    }

    @Override
    public void updateProjectById(int id, Project project) {
        projectDao.updateProjectById(id, project);
    }
}
