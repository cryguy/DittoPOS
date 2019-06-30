package DittoPOS.products;
import DittoPOS.helpers.Json;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * TODO: We can either go with this, or have another option to always be able to sell regardless of stock
 */


public class ProductManagement {

    /**
     * declare variable of product management
     */
    // TODO : ADD method to add stock
    // StockManagement is an object that contains the array of Stocks
    ArrayList<SaleProduct> products = new ArrayList<>();
    private static ProductManagement instance = null;

    private ProductManagement() {
    }

    /**
     * Prevents us from making more than 1 instance and causing problems
     * @return the object of productManagement
     */

    public synchronized static ProductManagement getInstance() {
        if(instance == null) {
            instance = new ProductManagement();
        }
        return instance;
    }


    public void addProduct(String name, double price, String barcode) {
        this.products.add(new SaleProduct(name, price, false, barcode, 0, null));
    }

    public void saveProduct(SaleProduct product, String name, double price, String barcode) {
        product.getProduct().setName(name);
        product.getProduct().setPrice(price);
        product.getProduct().setBarcode(barcode);
    }

    public SaleProduct getProduct(String name) {
        for (SaleProduct i : products) {
            if (i.getProduct().getName().equals(name))
                return i;
        }
        return null;
    }
    /**
     * serialize the current object into json
     * @return String of json
     */
    public String toJson() {
        return Json.a.toJson(products);
    }

    /**
     * restore the object using json data
     * @param json the input of json string
     */
    public void setProducts(String json) {
        products = Json.a.fromJson(json, new TypeToken<ArrayList<SaleProduct>>() {
        }.getType());
    }

    /**
     * Delete Product from product list
     * @param i product to delete
     */
    public void deleteProduct(SaleProduct i) {
        products.remove(i);
    }

    public ArrayList<SaleProduct> getProducts() {
        return products;
    }
}
