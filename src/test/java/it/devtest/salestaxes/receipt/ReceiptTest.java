package it.devtest.salestaxes.receipt;

import it.devtest.salestaxes.loader.OrderItemLoader;
import it.devtest.salestaxes.order.Order;
import org.junit.Assert;
import org.junit.Test;

public class ReceiptTest {

    @Test
    public void test1() {
        Order order = new Order();
        order.addItem(OrderItemLoader.loadOrderItem("1 book at 12.49"));
        order.addItem(OrderItemLoader.loadOrderItem("1 music CD at 14.99"));
        order.addItem(OrderItemLoader.loadOrderItem("1 chocolate bar at 0.85"));

        String expectedReceipt = "1 book: 12.49\n" +
                                 "1 music CD: 16.49\n" +
                                 "1 chocolate bar: 0.85\n" +
                                 "Sales Taxes: 1.50\n" +
                                 "Total: 29.83\n";

       Assert.assertEquals(expectedReceipt, new Receipt(order).print());
    }

    @Test
    public void test2() {
        Order order = new Order();
        order.addItem(OrderItemLoader.loadOrderItem("1 imported box of chocolates at 10.00"));
        order.addItem(OrderItemLoader.loadOrderItem("1 imported bottle of perfume at 47.50"));

        String expectedReceipt = "1 imported box of chocolates: 10.50\n" +
                                 "1 imported bottle of perfume: 54.65\n" +
                                 "Sales Taxes: 7.65\n" +
                                 "Total: 65.15\n";

        Assert.assertEquals(expectedReceipt, new Receipt(order).print());
    }

    @Test
    public void test3() {
        Order order = new Order();
        order.addItem(OrderItemLoader.loadOrderItem("1 imported bottle of perfume at 27.99"));
        order.addItem(OrderItemLoader.loadOrderItem("1 bottle of perfume at 18.99"));
        order.addItem(OrderItemLoader.loadOrderItem("1 packet of headache pills at 9.75"));
        order.addItem(OrderItemLoader.loadOrderItem("1 imported box of chocolates at 11.25"));

        String expectedReceipt = "1 imported bottle of perfume: 32.19\n" +
                                 "1 bottle of perfume: 20.89\n" +
                                 "1 packet of headache pills: 9.75\n" +
                                 "1 imported box of chocolates: 11.85\n" +
                                 "Sales Taxes: 6.70\n" +
                                 "Total: 74.68\n";

        Assert.assertEquals(expectedReceipt, new Receipt(order).print());
    }

}
