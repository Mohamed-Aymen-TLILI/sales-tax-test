package com.sales.tax;

import java.math.BigDecimal;

public class ProductParser {
    private static final String LINE_FORMAT = "(?<quantity>\\d*) (?<description>[\\w\\s]*)(?: imported)? at (?<price>\\d*\\.\\d*)";

    public static Product parseProduct(String inputLine) {
        return new Product(
                parseDescription(inputLine),
                new BigDecimal(parsePrice(inputLine)),
                parseQuantity(inputLine),
                isImported(inputLine)
        );
    }

    private static String parseDescription(String inputLine) {
        return RegexMatcher.extractValue(inputLine, LINE_FORMAT, "description");
    }

    private static String parsePrice(String inputLine) {
        return RegexMatcher.extractValue(inputLine, LINE_FORMAT, "price");
    }

    private static int parseQuantity(String inputLine) {
        String quantityString = RegexMatcher.extractValue(inputLine, LINE_FORMAT, "quantity");
        return quantityString.isEmpty() ? 1 : Integer.parseInt(quantityString);
    }

    private static boolean isImported(String inputLine) {
        return inputLine.contains("imported");
    }
}