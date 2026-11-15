package hu.yourname.quoteforge.project;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> findAll() {
        return projectService.findAll();
    }

    @PostMapping("/customer/{customerId}")
    public Project create(
            @PathVariable Long customerId,
            @RequestBody Project project) {

        return projectService.create(customerId, project);
    }
    
    @GetMapping("/customer/{customerId}")
    public List<Project> findByCustomerId(
            @PathVariable Long customerId) {

        return projectService.findByCustomerId(customerId);
    }
    
    @GetMapping("/{id}")
    public Project findById(@PathVariable Long id) {
        return projectService.findById(id);
    }
    
    @PutMapping("/{id}")
    public Project update(
            @PathVariable Long id,
            @RequestBody Project project) {

        return projectService.update(id, project);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }
}