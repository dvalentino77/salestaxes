package it.devtest.salestaxes.receipt;

import it.devtest.salestaxes.order.OrderItem;
import it.devtest.salestaxes.product.ProductType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ReceiptItem {
    private static final BigDecimal SALE_TAX_RATE = BigDecimal.valueOf(0.10);
    private static final BigDecimal IMPORT_TAX_RATE = BigDecimal.valueOf(0.05);

    private OrderItem orderItem;
    private BigDecimal billableAmount;
    private BigDecimal saleTaxAmount;
    private BigDecimal importTaxAmount;

    public ReceiptItem(OrderItem orderItem) {
        this.orderItem = orderItem;

        setSaleTaxAmount();
        setImportTaxAmount();
        setBillableAmount();
    }

    private void setBillableAmount() {
        billableAmount = orderItem.getPrice().add(saleTaxAmount).add(importTaxAmount);
    }

    private void setSaleTaxAmount() {
        BigDecimal taxRate = orderItem.getProduct().getType().equals(ProductType.Other) ? SALE_TAX_RATE : BigDecimal.ZERO;
        saleTaxAmount = getTaxAmount(orderItem.getPrice(), taxRate);
    }

    private void setImportTaxAmount() {
        BigDecimal taxRate = orderItem.getProduct().isImported() ? IMPORT_TAX_RATE : BigDecimal.ZERO;
        importTaxAmount = getTaxAmount(orderItem.getPrice(), taxRate);
   }

    private BigDecimal getTaxAmount(BigDecimal price, BigDecimal rate) {
        BigDecimal taxAmount = price.multiply(rate);
        return roundValueToTheNearestFiveCents(taxAmount);
    }

    private BigDecimal roundValueToTheNearestFiveCents(BigDecimal value) {
        return value
                .divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP)
                .multiply(BigDecimal.valueOf(0.05))
                .setScale(2);
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public BigDecimal getBillableAmount() {
        return billableAmount;
    }

    public BigDecimal getSaleTaxAmount() {
        return saleTaxAmount;
    }

    public BigDecimal getImportTaxAmount() {
        return importTaxAmount;
    }

}
