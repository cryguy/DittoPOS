package DittoPOS.helpers;

import DittoPOS.administration.UserManagement;
import DittoPOS.products.CategoryManagement;
import DittoPOS.products.ProductManagement;
import DittoPOS.reports.CashFlow;
import DittoPOS.sales.ReceiptManagement;
import com.google.gson.Gson;


public class Json {

    /**
     * declare the datatype in class Json
     */
    public final static Gson a = new Gson();
    private final String productJson;
    private final String receiptJson;
    private final String userJson;
    private final String categoryJson;
    private final String cashFlowJson;

    private final String cashFlowReJson;


    /**
     *Default constructor of Json to get the details store in Json
     */
    private Json() {
        this.productJson = ProductManagement.getInstance().toJson();
        this.userJson = UserManagement.getInstance().toJson();
        this.receiptJson = UserManagement.getInstance().toJson();
        this.categoryJson = CategoryManagement.getInstance().toJson();
        this.cashFlowJson = CashFlow.getInstance().toJsonCash();
        this.cashFlowReJson = CashFlow.getInstance().toJsonRe();
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


    public static String printString() {
        return new Gson().toJson(new Json());
    }


    /**
     *Restore the information into Productmanagement, stockmanagement and ordermanagement
     */
    public void restoreAll() {
        ProductManagement.getInstance().setProducts(this.productJson);
        UserManagement.getInstance().setUsers(this.userJson);
        ReceiptManagement.getInstance().setReceipt(this.receiptJson);
        CategoryManagement.getInstance().setCategories(this.categoryJson);
        CashFlow.getInstance().setLog(this.cashFlowReJson);
        CashFlow.getInstance().setMoneylog(this.cashFlowJson);
        //this.categoryJson = CategoryManagement.getInstance().toJson();
     //   OrderManagement.getInstance().setOrders(this.orderJson);
    }

}
