package DittoPOS.sales;

import DittoPOS.products.SaleProduct;

public class Item {
    private double price;
    private SaleProduct product;
    private int quantity;
    int discount = 0;
    // Add tax
    Item(SaleProduct product, int quantity){
        this.quantity = quantity;
        this.product = product;
        this.price = product.getProduct().getPrice();
    }

}
