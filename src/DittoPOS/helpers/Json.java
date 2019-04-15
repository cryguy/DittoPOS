package DittoPOS.helpers;

import DittoPOS.products.ProductManagement;
import DittoPOS.products.StockManagement;
import DittoPOS.sales.OrderManagement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Json {

    /**
     * declare the datatype in class Json
     */
    public final static Gson a = new Gson();
    private final String productJson;
    private final String stockJson;
    private final String orderJson;


    /**
     *Default constructor of Json to get the details store in Json
     */
    private Json() {
        this.productJson = ProductManagement.getInstance().toJson();
        this.orderJson = OrderManagement.getInstance().toJson();
        this.stockJson = StockManagement.getInstance().toJson();
    }

    /**
     *Store jsonString into Json and get the details from class Json
     * @param jsonString string to load from
     * @return Json object
     */


    public static Json fromString(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Json.class);
    }


    static void printString() {
        System.out.println(new Gson().toJson(new Json()));
    }

    static void prettyPrint() {
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new Json()));
    }


    /**
     *Restore the information into Productmanagement, stockmanagement and ordermanagement
     */
    public void restoreAll() {
        ProductManagement.getInstance().setProducts(this.productJson);
        StockManagement.getInstance().setStocks(this.stockJson);
        OrderManagement.getInstance().setOrders(this.orderJson);
    }

}
