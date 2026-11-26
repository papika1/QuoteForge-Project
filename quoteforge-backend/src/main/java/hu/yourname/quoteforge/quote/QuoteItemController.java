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
@RequestMapping("/api/quote-items")
public class QuoteItemController {

    private final QuoteItemService quoteItemService;

    public QuoteItemController(QuoteItemService quoteItemService) {
        this.quoteItemService = quoteItemService;
    }

    @GetMapping("/quote/{quoteId}/total")
    public BigDecimal getQuoteTotal(@PathVariable Long quoteId) {
        return quoteItemService.calculateQuoteTotal(quoteId);
    }
    
    @PostMapping("/quote/{quoteId}")
    public QuoteItem create(
            @PathVariable Long quoteId,
            @RequestBody QuoteItemCreateRequest request) {

        return quoteItemService.create(quoteId, request);
    }

    @GetMapping("/quote/{quoteId}")
    public List<QuoteItem> findByQuoteId(@PathVariable Long quoteId) {
        return quoteItemService.findByQuoteId(quoteId);
    }
}