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

    public QuoteItem create(Long quoteId, QuoteItem quoteItem) {

        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new RuntimeException("Quote not found"));

        Optional<QuoteItem> existingItem =
                quoteItemRepository.findByQuoteIdAndDescriptionAndUnitPrice(
                        quoteId,
                        quoteItem.getDescription(),
                        quoteItem.getUnitPrice()
                );

        if (existingItem.isPresent()) {
            QuoteItem item = existingItem.get();

            item.setQuantity(
                    item.getQuantity().add(quoteItem.getQuantity())
            );

            return quoteItemRepository.save(item);
        }

        quoteItem.setQuote(quote);

        return quoteItemRepository.save(quoteItem);
    }

    public List<QuoteItem> findByQuoteId(Long quoteId) {
        return quoteItemRepository.findByQuoteId(quoteId);
    }
}