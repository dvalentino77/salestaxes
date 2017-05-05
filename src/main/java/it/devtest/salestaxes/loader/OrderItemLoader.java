package it.devtest.salestaxes.loader;

import it.devtest.salestaxes.order.OrderItem;
import it.devtest.salestaxes.product.Product;
import it.devtest.salestaxes.product.ProductType;

import java.math.BigDecimal;

public class OrderItemLoader {

    public static OrderItem loadOrderItem (String itemDesc) {
        // Divide the current line into quantity-name & price data.
        String[] splittedItemDesc = itemDesc.split(" at ");

        // Divide the quantity and name from the extracted data.
        String[] quantityAndName = splittedItemDesc[0].split(" ", 2);
        String name = quantityAndName[1];
        Integer quantity = new Integer(quantityAndName[0]);
        BigDecimal price = new BigDecimal(splittedItemDesc[1]);

        // Product type definition
        ProductType type = getProductType(name);

        // Imported product
        boolean isImported = isProductImported(name);

        // Create OrderItem
        Product product = new Product(name, type, price, isImported);
        return new OrderItem(quantity, product);
    }

    private static ProductType getProductType(String productName) {
        if (productName.contains("book"))  {
            return ProductType.Book;
        } else if (productName.contains("chocolate"))  {
            return ProductType.Food;
        } else if (productName.contains("pills"))  {
            return ProductType.Book;
        } else return ProductType.Other;
   }

    private static boolean isProductImported(String productName) {
        return productName.contains("imported");
    }

}
