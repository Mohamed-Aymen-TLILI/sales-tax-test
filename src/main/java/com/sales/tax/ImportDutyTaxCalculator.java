package com.sales.tax;

import java.math.BigDecimal;

public class ImportDutyTaxCalculator implements TaxCalculator {
    private static final BigDecimal IMPORT_DUTY_RATE = new BigDecimal("0.05");

    @Override
    public BigDecimal calculateTax(BigDecimal price, boolean isImported, boolean isExempt) {
        if (isImported) {
            return price.multiply(IMPORT_DUTY_RATE);
        }
        return BigDecimal.ZERO;
    }
}
