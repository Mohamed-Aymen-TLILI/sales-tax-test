package com.sales.tax;

import java.math.BigDecimal;

public interface TaxCalculator {
    BigDecimal calculateTax(BigDecimal price, boolean isImported, boolean isExempt);

}
