package DittoPOS.sales;
import DittoPOS.helpers.Json;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


/*
TODO : Decide to keep and reuse or implement new "order management" // needs a lot of refactoring
 */


public class OrderManagement {


    /**
     * singleton of ordermanagement to make sure it will only has one access at the same time
     */

    private static OrderManagement instance = null;
    /**
     * array list or ordermanagement
     */
    private ArrayList<Order> orders = new ArrayList<>();
    /**
     * declare datatype of counter
     */
    private int counter = 1;


    /**
     * default constructor of ordermanagement and cannot directly access by public
     */

    private OrderManagement() {
    }


    /**
     *make sure it will have only one can access to OrderManagement at the same time
     * and check the instance is it null, if it is, it will create a new ordermanagement object
     * @return object of OrderManagement
     */

    public synchronized static OrderManagement getInstance() {
        if (instance == null) { // check is it instance exist
            instance = new OrderManagement(); //if instance exist, create a new object for OrderManagement
        }
        return instance;
    }


    /**
     * Method to check the orders arraylist is it empty
     * if it is empty, remove the array list
     */

    public void CheckEmptyOrderAndRemove() { //Method to check if the order is empty
        orders.removeIf((Order order) -> { // Remove the order list if the condition meet
            boolean ret = order.GetOrderProduct().isEmpty();
            if (ret)
                System.out.println("Deleting Order - " + order.getName() + "   which is empty"); // added this for the logging , can be removed and used as a 1 liner
            return ret;

        });
        // orders.removeIf((Order order) -> order.GetOrderProduct().isEmpty());
    }
            /*

            the one on top is basically this but using a lambda function which is a function which is anonymous
            the function only exists for that function call, and its alot faster than the way below, efficiency is
            time complexity is O(n) vs O(n2) which is the code below


            while (iter.hasNext())
            if (iter.next().GetOrderProduct().isEmpty()) {
                System.out.println("Deleting Order - " + iter.next().getName() + "   which is empty");
                iter.remove();
            }
            */


    /**
     *if meet new order counter will +1 and in arraylist of order will +1
     * @return a Order object
     */

    public Order NewOrder() {
        Order i = new Order(counter++);
        orders.add(i);
        return i;
        // counter works better than size, because we will
        // need to check for a lot of other stuff if we use size()+1
    }


    /**
     *Get the details in order arraylist
     * @param i index of order to get
     * @return to new order
     */

    Order GetOrder(int i) {
        for (Order order : this.orders) {
            if (order.getName() == i) {
                return order;
            }
        }
        return NewOrder(); // wont ever happen but who knows...
    }


    /**
     *method to save order and add the order in orders arraylist
     * @param i order to save
     */

    void SaveOrder(Order i) {
        orders.add(i);
    }


    /**
     *method to delete order and remove the order in orders arraylist
     * @param i order to delete
     */

    public void DeleteOrder(Order i) {
        orders.remove(i);
    }


    /**
     *set the orders arraylist from json
     * @param json string to restore from
     */

    public void setOrders(String json) {
        orders = Json.a.fromJson(json, new TypeToken<ArrayList<Order>>() {
        }.getType());
    }


    /**
     * @return orders details from json to string
     */

    public String toJson() {
        return Json.a.toJson(orders);
    }


    /**
     *show the orders in arraylist order
     * @return orders details in arraylist
     */
    public ArrayList<Order> getOrderArray() {
        return this.orders;
    }
}