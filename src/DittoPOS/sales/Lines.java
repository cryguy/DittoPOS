package DittoPOS.sales;

import DittoPOS.products.SaleProduct;

import java.util.ArrayList;

public class Lines {
    ArrayList<Lines> child = new ArrayList<>();
    String barcode;
    boolean isBundle = false;
    private double price;
    private int quantity;
    // Add tax

    /*Lines( product, int quantity) {
        this.quantity = quantity;
        this.product = product;
        this.price = product.getProduct().getPrice();
    }*/

}
