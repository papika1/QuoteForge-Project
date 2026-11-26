package hu.yourname.quoteforge.quote;

import java.math.BigDecimal;

public class QuoteSummary {

    private BigDecimal netTotal;
    private BigDecimal vat;
    private BigDecimal grossTotal;

    public QuoteSummary(
            BigDecimal netTotal,
            BigDecimal vat,
            BigDecimal grossTotal) {

        this.netTotal = netTotal;
        this.vat = vat;
        this.grossTotal = grossTotal;
    }

    public BigDecimal getNetTotal() {
        return netTotal;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public BigDecimal getGrossTotal() {
        return grossTotal;
    }
}