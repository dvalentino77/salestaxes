package it.devtest.salestaxes.order;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items;

    public Order() {
        items = new ArrayList<OrderItem>();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
