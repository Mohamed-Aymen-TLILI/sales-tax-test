package com.sales.tax;

import java.math.BigDecimal;

public class Product {
    private String description;
    private BigDecimal price;
    private int quantity;
    private boolean isImported;

    public Product(String description, BigDecimal price, int quantity, boolean isImported) {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.isImported = isImported;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isImported() {
        return isImported;
    }
}

