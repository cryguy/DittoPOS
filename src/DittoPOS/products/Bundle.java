package DittoPOS.products;


import java.util.ArrayList;
import java.util.Date;

public class Bundle {

    public static ArrayList<SaleProduct> BundleProducts = new ArrayList<>();
    private String name;
    private String reference;
    private double price;
    private Date validUntil;


    public Bundle(String name) {
        this.name = name;


    }

    public static ArrayList<SaleProduct> getBundleProducts() {
        return BundleProducts;
    }

    public static void setBundleProducts(ArrayList<SaleProduct> bundleProducts) {
        BundleProducts = bundleProducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }
}










