package hu.yourname.quoteforge.quote;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class QuoteItemService {

    private final QuoteItemRepository quoteItemRepository;
    private final QuoteRepository quoteRepository;

    public QuoteItemService(
            QuoteItemRepository quoteItemRepository,
            QuoteRepository quoteRepository) {

        this.quoteItemRepository = quoteItemRepository;
        this.quoteRepository = quoteRepository;
    }
    
    public BigDecimal calculateQuoteTotal(Long quoteId) {
        List<QuoteItem> items = quoteItemRepository.findByQuoteId(quoteId);

        return items.stream()
                .map(QuoteItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public QuoteItem create(Long quoteId, QuoteItemCreateRequest request) {

        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new RuntimeException("Quote not found"));

        Optional<QuoteItem> existingItem =
                quoteItemRepository.findByQuoteIdAndDescriptionAndUnitPrice(
                        quoteId,
                        request.getDescription(),
                        request.getUnitPrice()
                );

        if (existingItem.isPresent()) {
            QuoteItem item = existingItem.get();

            item.setQuantity(
                    item.getQuantity().add(request.getQuantity())
            );

            return quoteItemRepository.save(item);
        }

        QuoteItem quoteItem = new QuoteItem();

        quoteItem.setDescription(request.getDescription());
        quoteItem.setQuantity(request.getQuantity());
        quoteItem.setUnitPrice(request.getUnitPrice());
        quoteItem.setQuote(quote);

        return quoteItemRepository.save(quoteItem);
    }

    public List<QuoteItem> findByQuoteId(Long quoteId) {
        return quoteItemRepository.findByQuoteId(quoteId);
    }
}