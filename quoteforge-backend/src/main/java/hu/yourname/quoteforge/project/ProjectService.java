package hu.yourname.quoteforge.project;

import java.util.List;

import org.springframework.stereotype.Service;

import hu.yourname.quoteforge.customer.Customer;
import hu.yourname.quoteforge.customer.CustomerService;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final CustomerService customerService;

    public ProjectService(
            ProjectRepository projectRepository,
            CustomerService customerService) {

        this.projectRepository = projectRepository;
        this.customerService = customerService;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project create(Long customerId, Project project) {
        Customer customer = customerService.findById(customerId);

        project.setCustomer(customer);

        return projectRepository.save(project);
    }
    
    public List<Project> findByCustomerId(Long customerId) {
        customerService.findById(customerId);

        return projectRepository.findByCustomerId(customerId);
    }
    
    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }
    
    public Project update(Long id, Project updatedProject) {
        Project existingProject = findById(id);

        existingProject.setName(updatedProject.getName());
        existingProject.setDescription(updatedProject.getDescription());

        return projectRepository.save(existingProject);
    }
    
    public void delete(Long id) {
        Project project = findById(id);
        projectRepository.delete(project);
    }
}