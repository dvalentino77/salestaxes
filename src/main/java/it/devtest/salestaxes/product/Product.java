package it.devtest.salestaxes.product;

import java.math.BigDecimal;

public class Product {

    private String name;
    private ProductType type;
    private BigDecimal price;
    private boolean imported;

    public Product(String name, ProductType type, BigDecimal price, boolean imported) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.imported = imported;
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isImported() {
        return imported;
    }

}