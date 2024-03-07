package com.sales.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private static final BigDecimal ROUNDING_FACTOR = new BigDecimal("0.05");
    private List<Product> items;
    private TaxCalculator basicTaxCalculator;
    private TaxCalculator importDutyTaxCalculator;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.basicTaxCalculator = new BasicTaxCalculator();
        this.importDutyTaxCalculator = new ImportDutyTaxCalculator();
    }

    public void addItem(String inputLine) {
        Product product = ProductParser.parseProduct(inputLine);
        items.add(product);
    }

    public List<Product> getItems() {
        return items;
    }

    public void generateReceipt() {
        BigDecimal totalSalesTax = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Product product : items) {
            BigDecimal itemSalesTax = calculateItemSalesTax(product);
            BigDecimal itemTotal = product.getPrice().add(itemSalesTax).multiply(BigDecimal.valueOf(product.getQuantity()));

            totalSalesTax = totalSalesTax.add(itemSalesTax.multiply(BigDecimal.valueOf(product.getQuantity())));
            totalPrice = totalPrice.add(itemTotal);

            System.out.println(product.getQuantity() + " " + product.getDescription() + " : " + itemTotal);
        }

        System.out.println("Sales Taxes : " + totalSalesTax.setScale(2, RoundingMode.HALF_UP));
        System.out.println("Total : " + totalPrice.setScale(2, RoundingMode.HALF_UP));
    }

    private BigDecimal calculateItemSalesTax(Product product) {
        BigDecimal basicTax = basicTaxCalculator.calculateTax(product.getPrice(), product.isImported(), isExempt(product.getDescription()));
        BigDecimal importDutyTax = importDutyTaxCalculator.calculateTax(product.getPrice(), product.isImported(), isExempt(product.getDescription()));
        return roundSalesTax(basicTax.add(importDutyTax));
    }

    private BigDecimal roundSalesTax(BigDecimal salesTax) {
        BigDecimal roundedTax = salesTax.divide(ROUNDING_FACTOR, 0, RoundingMode.UP);
        return roundedTax.multiply(ROUNDING_FACTOR);
    }

    private boolean isExempt(String description) {
        return ExemptItems.isExempt(description);
    }
}