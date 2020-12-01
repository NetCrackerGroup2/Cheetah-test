package com.ncedu.cheetahtest.controller.project;

import com.ncedu.cheetahtest.entity.project.Project;
import com.ncedu.cheetahtest.entity.project.ProjectDto;
import com.ncedu.cheetahtest.entity.project.ProjectResponse;
import com.ncedu.cheetahtest.entity.project.ResponseProjectPaginated;
import com.ncedu.cheetahtest.service.project.ProjectService;
import com.ncedu.cheetahtest.service.project.ProjectServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project-management/projects")
@CrossOrigin(origins = "${frontend.ulr}")
@Slf4j
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectDto projectDto) {
        projectService.createNewProject(projectDto);
        return ResponseEntity.ok(new ProjectResponse("A new project has been created successfully!"));
    }

    @GetMapping
    public ResponseProjectPaginated getAllProjects(@RequestParam int page,
                                                   @RequestParam int size) {
        return projectService.getAllProjects(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping("/search")
    public ResponseProjectPaginated getProjectsByTitle(@RequestParam int page,
                                                       @RequestParam int size,
                                                       @RequestParam String title) {

        return projectService.getProjectsPaginatedByTitle(page, size, title);
    }

    @GetMapping("/archive")
    public ResponseEntity<List<Project>> getAllArchivedProjects() {
        return ResponseEntity.ok(projectService.getAllArchievedProjects());
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable int id,
                                                         @RequestBody Project project) {
        projectService.updateProjectById(id, project);
        return ResponseEntity.ok(new ProjectResponse("The project has been updated!"));
    }

    @PutMapping("/archive/{id}")
    public ResponseEntity<ProjectResponse> setArchivedStatus(@PathVariable int id) {
        projectService.setArchievedStatus(id);
        return ResponseEntity.ok(new ProjectResponse("A project No. " + id + " has been archived!"));
    }

    @GetMapping("/active")
    public ResponseProjectPaginated getActiveProjects(@RequestParam int page,
                                                      @RequestParam int size) {
        return projectService.getActiveProjects(page, size);
    }

    @GetMapping("/search/findActiveByTitle")
    public ResponseProjectPaginated getActiveProjectsByTitle(@RequestParam int page,
                                                             @RequestParam int size,
                                                             @RequestParam String title) {
        return projectService.getActiveProjectsByTitle(page, size, title);
    }
}
