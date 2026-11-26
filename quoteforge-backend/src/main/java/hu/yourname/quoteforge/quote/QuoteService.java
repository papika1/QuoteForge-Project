package hu.yourname.quoteforge.quote;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import hu.yourname.quoteforge.project.Project;
import hu.yourname.quoteforge.project.ProjectService;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final ProjectService projectService;
    private final QuoteItemService quoteItemService;

    public QuoteService(
            QuoteRepository quoteRepository,
            ProjectService projectService,
            QuoteItemService quoteItemService) {

        this.quoteRepository = quoteRepository;
        this.projectService = projectService;
		this.quoteItemService = quoteItemService;
    }
    
    public QuoteSummary getSummary(Long quoteId) {

        BigDecimal net = calculateNetTotal(quoteId);
        BigDecimal vat = calculateVat(quoteId);
        BigDecimal gross = calculateGrossTotal(quoteId);

        return new QuoteSummary(net, vat, gross);
    }
    
    public BigDecimal calculateNetTotal(Long quoteId) {
        return quoteItemService.calculateQuoteTotal(quoteId);
    }
    
    public BigDecimal calculateVat(Long quoteId) {
        BigDecimal net = calculateNetTotal(quoteId);

        return net.multiply(new BigDecimal("0.27"));
    }
    
    public BigDecimal calculateGrossTotal(Long quoteId) {
        BigDecimal net = calculateNetTotal(quoteId);
        BigDecimal vat = calculateVat(quoteId);

        return net.add(vat);
    }

    public BigDecimal calculateTotal(Long quoteId) {
        return quoteItemService.calculateQuoteTotal(quoteId);
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