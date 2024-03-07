package com.sales.tax;

import java.util.List;

public class ExemptItems {
    private static final List<String> EXEMPT_ITEMS = List.of("book", "pills", "chocolate");

    public static boolean isExempt(String description) {
        return EXEMPT_ITEMS.stream().anyMatch(exemptItem -> description.toLowerCase().contains(exemptItem));
    }
}
