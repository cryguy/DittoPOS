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
    public static ArrayList<SaleProduct> products = new ArrayList<>();
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
    public void DeleteProduct(SaleProduct i) {
        products.remove(i);
    }

}
