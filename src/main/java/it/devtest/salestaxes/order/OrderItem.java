package it.devtest.salestaxes.order;

import it.devtest.salestaxes.product.Product;

import java.math.BigDecimal;

public class OrderItem {
    private int quantity;
    private Product product;

    public OrderItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getPrice() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

}
