package com.sales.tax;

import java.math.BigDecimal;

public class BasicTaxCalculator implements TaxCalculator {
    private static final BigDecimal BASIC_SALES_TAX_RATE = new BigDecimal("0.10");

    @Override
    public BigDecimal calculateTax(BigDecimal price, boolean isImported, boolean isExempt) {
        if (!isExempt) {
            return price.multiply(BASIC_SALES_TAX_RATE);
        }
        return BigDecimal.ZERO;
    }
}