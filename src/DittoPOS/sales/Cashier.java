package DittoPOS.sales;

import DittoPOS.helpers.Menu;
import DittoPOS.helpers.input;

/*
TODO Return Price, Loop to find Price, After paid call Order Management and delete order

TODO : Decide if we want to continue using this or completely revamp and include CashFlow or just separate it?


 */
public class Cashier {
    private static double cash;


    /**
     * Pay for order
     * @param i Order to pay for
     */
    public static void payForOrder(Order i){
        showOrder(i);
        double paid = Menu.getInput("Enter Money Paid - ", new input.DoubleInputGrabber());
        System.out.println("Change is : RM" + (paid-getPrice(i)));
        cash += getPrice(i);
        OrderManagement.getInstance().DeleteOrder(i);
    }

    /**
     * Getting the thing store in order array list and print out the details
     */
    public static void showOrders() {
        for (Order i : OrderManagement.getInstance().getOrderArray()) {
            System.out.println(i.toString());

        }
    }

    /**
     * Prints out the current Cash
     */
    public static void getCash(){
        System.out.println("Current Cash in cashier - " + cash);
    }
    /**
     * print out the details of order and get the price of the order
     * @param i Show order details and price
     */
    private static void showOrder(Order i) { //show the order and the price
        System.out.println(i.toString());
        System.out.println("Total Price : " + getPrice(i));
    }

    /**
     * get price of the order
     * @param i Order to get price from
     * @return price of order
     */

    static private double getPrice(Order i) {
        return i.getPrice();
    }
}

