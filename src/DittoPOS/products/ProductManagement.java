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
    public static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Product> availableProducts = new ArrayList<>();
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
     *to print out the product that available
     * @return available product details
     */
    public ArrayList<Product> availableProducts() {
        updateAvailable();
        for (Product i : availableProducts)
            i.setQuantity(1);
        return availableProducts;
    }


    /**
     * to check the product that is available now
     * @param stocks stock list to check
     * @param product product to check
     * @return true or false depending on the availability of product
     */
    private boolean productAvailable(ArrayList<Stock> stocks, Product product) {
        //boolean[] t = new boolean[product.getIngredients().size()];
        //int incre = 0;
        /*
        for (Ingredient i : product.getIngredients())
            for (Stock j : stocks)
                if (j.GetName().equals(i.GetName())) {
                    if (!(j.getLeft() >= i.GetNeeded()))
                        return false;
                    break; // break out of 1 for loop as we found it already, don't need to go to the next if we found it
                }
                */
        return true;
    }


    /**
     *print the product that have been ordered
     */

    public void PrintProduct() {
        int x = 0;
        for (Product i : products) {
            System.out.printf("%d\t %s\t\t  %.2f%n", ++x, i.getName(), i.getPrice());
            //System.out.println(++x + ". " + i.GetName() + " " + i.getLeft());
        }
    }


    /**
     *show the product that have ingredient in stock to make
     * @return return product that is available
     * @param products product list to check
     * @param stocks stock list to check from
     */
    private ArrayList<Product> showAllowedProduct(ArrayList<Stock> stocks, ArrayList<Product> products) {
        ArrayList<Product> returnproduct = new ArrayList<>();
        for (Product product : products) {
            if (productAvailable(stocks, product))
                returnproduct.add(product);
        }
        return returnproduct;
    }

    /**
     * serialize the current object into json
     * @return String of json
     */
    public String toJson() {
        return Json.a.toJson(products);
    }

    /**
     * Updates the current available products
     */
    public void updateAvailable() {
        for (Product i : availableProducts)
            i.setQuantity(1);
        availableProducts = showAllowedProduct(StockManagement.getInstance().stocks, products);
    }

    /**
     * restore the object using json data
     * @param json the input of json string
     */
    public void setProducts(String json) {
        products = Json.a.fromJson(json, new TypeToken<ArrayList<Product>>() {
        }.getType());
    }

    /**
     * Delete Product from product list
     * @param i product to delete
     */
    public void DeleteProduct(Product i) {
        products.remove(i);
        updateAvailable();
    }

}
