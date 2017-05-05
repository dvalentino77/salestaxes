package it.devtest.salestaxes.receipt;

import it.devtest.salestaxes.order.Order;
import it.devtest.salestaxes.order.OrderItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<ReceiptItem> receiptItems;
    private BigDecimal salesTaxes;
    private BigDecimal totalAmount;
    
    public Receipt (Order order) {
        initialize(order);
    }

    private void initialize(Order order) {
        loadReceiptItems(order);
        calculateSalesTaxes();
        calculateTotalAmount();
    }

    private void loadReceiptItems(Order order) {
        receiptItems = new ArrayList<ReceiptItem>();

        for (OrderItem orderItem : order.getItems()) {
            ReceiptItem receiptItem = new ReceiptItem(orderItem);
            receiptItems.add(receiptItem);
        }

    }

    private void calculateSalesTaxes() {
        salesTaxes = BigDecimal.ZERO;

        for (ReceiptItem receiptItem : receiptItems) {
            salesTaxes = salesTaxes.add(receiptItem.getSaleTaxAmount());
            salesTaxes = salesTaxes.add(receiptItem.getImportTaxAmount());
        }

    }

    private void calculateTotalAmount() {
        totalAmount = BigDecimal.ZERO;

        for (ReceiptItem receiptItem : receiptItems) {
            totalAmount = totalAmount.add(receiptItem.getBillableAmount());
        }

    }

    public String print() {
        StringBuffer items = printReceiptItems();
        StringBuffer taxes = printReceiptTaxes();
        StringBuffer total = printReceiptTotal();

        return new String(items.append(taxes).append(total));
    }

    private StringBuffer printReceiptItems() {
        StringBuffer items = new StringBuffer();

        for (ReceiptItem receiptItem : receiptItems) {
            items.append(receiptItem.getOrderItem().getQuantity());
            items.append(" ");
            items.append(receiptItem.getOrderItem().getProduct().getName());
            items.append(": ");
            items.append(receiptItem.getBillableAmount());
            items.append("\n");
        }

        return items;
    }

    private StringBuffer printReceiptTaxes() {
        StringBuffer taxes = new StringBuffer();
        taxes.append("Sales Taxes: ").append(salesTaxes).append("\n");
        return taxes;
    }

    private StringBuffer printReceiptTotal() {
        StringBuffer total = new StringBuffer();
        total.append("Total: ").append(totalAmount).append("\n");
        return total;
    }

}
