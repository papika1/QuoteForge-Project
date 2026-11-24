package hu.yourname.quoteforge.quote;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/{id}/total")
    public BigDecimal getTotal(@PathVariable Long id) {
        return quoteService.calculateTotal(id);
    }
    
    @GetMapping
    public List<Quote> findAll() {
        return quoteService.findAll();
    }

    @PostMapping("/project/{projectId}")
    public Quote create(
            @PathVariable Long projectId,
            @RequestBody Quote quote) {

        return quoteService.create(projectId, quote);
    }
}