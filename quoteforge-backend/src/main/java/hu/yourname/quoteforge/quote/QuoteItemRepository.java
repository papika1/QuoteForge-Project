package hu.yourname.quoteforge.quote;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteItemRepository extends JpaRepository<QuoteItem, Long> {

    List<QuoteItem> findByQuoteId(Long quoteId);
    
    Optional<QuoteItem> findByQuoteIdAndDescriptionAndUnitPrice(
            Long quoteId,
            String description,
            BigDecimal unitPrice
    );
}