package com.sales.tax;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoppingCartTest {

    @Test
    void testGenerateReceipt_Input1() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("1 book at 12.49");
        cart.addItem("1 music CD at 14.99");
        cart.addItem("1 chocolate bar at 0.85");

        List<Product> items = cart.getItems();
        assertEquals(3, items.size());

        BigDecimal expectedTotalSalesTax = new BigDecimal("1.50");
        BigDecimal expectedTotalPrice = new BigDecimal("29.83");

        assertReceipt(cart, expectedTotalSalesTax, expectedTotalPrice);
    }

    @Test
    void testGenerateReceipt_Input2() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("1 imported box of chocolates at 10.00");
        cart.addItem("1 imported bottle of perfume at 47.50");

        List<Product> items = cart.getItems();
        assertEquals(2, items.size());

        BigDecimal expectedTotalSalesTax = new BigDecimal("7.65");
        BigDecimal expectedTotalPrice = new BigDecimal("65.15");

        assertReceipt(cart, expectedTotalSalesTax, expectedTotalPrice);
    }

    @Test
    void testGenerateReceipt_Input3() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("1 imported bottle of perfume at 27.99");
        cart.addItem("1 bottle of perfume at 18.99");
        cart.addItem("1 packet of headache pills at 9.75");
        cart.addItem("1 box of imported chocolates at 11.25");

        List<Product> items = cart.getItems();
        assertEquals(4, items.size());

        BigDecimal expectedTotalSalesTax = new BigDecimal("6.70");
        BigDecimal expectedTotalPrice = new BigDecimal("74.68");

        assertReceipt(cart, expectedTotalSalesTax, expectedTotalPrice);
    }

    private void assertReceipt(ShoppingCart cart, BigDecimal expectedTotalSalesTax, BigDecimal expectedTotalPrice) {
        TestConsoleOutput testOutput = new TestConsoleOutput();
        System.setOut(testOutput);

        cart.generateReceipt();

        String consoleOutput = testOutput.getOutput();
        String[] lines = consoleOutput.split(System.lineSeparator());

        BigDecimal actualTotalSalesTax = new BigDecimal(lines[lines.length - 2].split(":")[1].trim());
        BigDecimal actualTotalPrice = new BigDecimal(lines[lines.length - 1].split(":")[1].trim());

        assertEquals(expectedTotalSalesTax, actualTotalSalesTax);
        assertEquals(expectedTotalPrice, actualTotalPrice);
    }

    private static class TestConsoleOutput extends java.io.PrintStream {
        private final StringBuilder stringBuilder = new StringBuilder();

        TestConsoleOutput() {
            super(System.out);
        }

        @Override
        public void println(String x) {
            stringBuilder.append(x).append(System.lineSeparator());
            super.println(x);
        }

        public String getOutput() {
            return stringBuilder.toString();
        }
    }
}
