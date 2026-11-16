package hu.yourname.quoteforge.quote;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import hu.yourname.quoteforge.project.Project;
import hu.yourname.quoteforge.project.ProjectService;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final ProjectService projectService;

    public QuoteService(
            QuoteRepository quoteRepository,
            ProjectService projectService) {

        this.quoteRepository = quoteRepository;
        this.projectService = projectService;
    }

    public List<Quote> findAll() {
        return quoteRepository.findAll();
    }

    public Quote create(Long projectId, Quote quote) {
        Project project = projectService.findById(projectId);

        quote.setProject(project);
        quote.setCreatedAt(LocalDate.now());

        return quoteRepository.save(quote);
    }
}