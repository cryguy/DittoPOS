package DittoPOS.Helpers;
import java.util.ArrayList;
import java.util.Scanner;


/*
Depreciated TODO: Either refactor everything to fit our needs or just remove all together
 */

public class Menu {

    final private static Scanner input = new Scanner(System.in);

    /**
     * Default constructor of menu
     */
    public Menu() {
    }

    // clear console

    /**
     *Declare the datatype of main_menu_op and showing the option to let user choose
     */

    public void mainMenu() {
        int main_menu_op;
        while (true) {
            OrderManagement.getInstance().CheckEmptyOrderAndRemove();
            System.out.print("----------------------\n Restaurant Chill Lah\n----------------------\n\n 1. Order\n 2. Order Management\n 3. Product Management\n 4. Inventory Management\n 5. End Day\n 6. Show Current Cash in Cashier\n 0. Exit\n\nChoose your option: ");
            main_menu_op = input.nextInt();
            switch (main_menu_op) {
                case 1:
                    //once user choose this option the orderMenu() will come up
                    orderMenu(OrderManagement.getInstance().NewOrder());
                    break;
                case 2:
                    System.out.print("2\n");
                    orderManage();
                    break;
                case 3:
                    System.out.print("3\n");
                    productManage();
                    break;
                case 4:
                    inventory();
                    break;
                case 5:
                    StockManagement.getInstance().endDay();
                    break;
                case 6:
                    Cashier.getCash();
                    input.nextLine();
                    System.out.print("Press Enter to continue...");
                    input.nextLine();
                    break;
                case 0:
                    Json.prettyPrint();
                    Json.printString();
                    return;
                default:
                    System.out.print("not found\n");
                    break;
            }
        }
    }


    /**
     *Check whether the thing store inside the product management is it empty and show respond
     * @param order Order to add to
     */
    private void orderMenu(Order order) {
        ProductManagement.getInstance().updateAvailable();
        //StockManagement.getInstance().PrintStocks();
        int selection;
        do {
            if (ProductManagement.getInstance().availableProducts().isEmpty() && order.GetOrderProduct().size() == 0) {
                System.out.println("ERROR - ALL PRODUCTS OUT OF STOCK!");
                return;
            } else if (ProductManagement.getInstance().availableProducts().isEmpty() && order.GetOrderProduct().size() != 0)
                break;

            int x = 0;
            for (Product i : ProductManagement.getInstance().availableProducts()) {
                System.out.println(++x + ": " + i.getName());
            }


            /*
             *show the order and confirm for the order if the selection from user is not equal to 0
             */
            System.out.println("0 : Show Ordered & Confirm Order");
            selection = input.nextInt();
            if (selection != 0 && selection >= 1 && (selection - 1) <= ProductManagement.getInstance().availableProducts().size() - 1) {
                try {
                    order.AddProduct(ProductManagement.getInstance().availableProducts().get(selection - 1).clone()); // WELL, FIXED IT, Apparently only the Pointer/Reference to object is added
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            } else if (selection != 0 && ((selection - 1) >= (ProductManagement.getInstance().availableProducts().size() - 1) || selection - 1 < 0)) {
                System.out.println("Error Please Select Again.");
            } else if (selection != 0) System.out.println("Error");

            ProductManagement.getInstance().updateAvailable();
        } while (selection != 0);
        System.out.println(order.toString());
    }


    /**
     * Allows user to manage product, allows adding, removing and showing products
     */
    private void productManage() {
        int menu;
        while (true) {
            System.out.print("Product Management\n\n 1. Print Products\n 2. Add Product\n 3. Remove Product\n 0. Exit\n\nChoose your option: ");
            menu = input.nextInt();
            input.nextLine();
            switch (menu) {
                case 1:

                    /*
                     *show all the product details that have store
                     */
                    System.out.println("---- Product List ----");
                    System.out.println("Num  Name        Price"); // 2 space after \t\t
                    if (ProductManagement.products.isEmpty())
                        System.out.println("Nothing is in here? add a new product?");
                    ProductManagement.getInstance().PrintProduct();
                    break;
                case 2:
                    /*
                     *If store is empty ask user need to store new in stock or not
                     */
                    if (StockManagement.getInstance().IsEmpty()) {
                        System.err.println("Nothing is in stock !");
                        System.err.println("Have you inserted any Stock?");
                        System.err.println("Press Enter to Continue");
                        input.nextLine();
                        break;
                    }


                    /*
                     * if user enter and continue prompt user to enter product name and price
                     */
                    System.out.print("Enter Name of Product : ");
                    String name = input.nextLine();
                    double price = getInput("Enter Price of " + name + " : ", new input.DoubleInputGrabber());
                    int inventoryselect;
                    ArrayList<Ingredient> productIngredient = new ArrayList<>();
                    System.out.println("---- Stock List ----");
                    System.out.println("Num  Name       Left");
                    StockManagement.getInstance().PrintStocks();
                    System.out.println("0. Exit/Done");
                    // here exit whille loop


                    outerloop:
                    while (true) {
                        do {
                            inventoryselect = getInput("Enter index of ingredient - ", new input.IntegerInputGrabber());
                            if (inventoryselect == 0)
                                break outerloop;
                        } while (inventoryselect - 1 < 0);
                        int needed = getInput("Enter number of \"" + StockManagement
                                .getInstance().stocks.get(inventoryselect - 1).getName() + " needed - ", new input.IntegerInputGrabber());
                        productIngredient.add(new Ingredient(StockManagement.getInstance().stocks.get(inventoryselect - 1).getName(),
                                StockManagement.getInstance().stocks.get(inventoryselect - 1).getPrice(), needed));
                    }
                    ProductManagement.products.add(new Product(name, price, productIngredient));
                    break;
                case 3:

                    System.out.println("---- Product List ----");

                    if (ProductManagement.products.isEmpty()) {
                        System.out.println("Nothing is added !");
                        break;
                    }
                    ProductManagement.getInstance().PrintProduct();
                    System.out.println();
                    int selection = getInput("Enter index to delete : ", new input.IntegerInputGrabber());

                    if (selection != 0 && selection >= 1 && (selection - 1) <= ProductManagement.products.size() - 1) {
                        ProductManagement.getInstance().DeleteProduct(ProductManagement.products.get(selection - 1));
                    } else if (selection != 0 && ((selection - 1) >= (ProductManagement.products.size() - 1) || selection - 1 < 0)) {
                        System.out.println("Error Please Select Again.");
                    } else if (selection != 0) System.out.println("Error");

                    break;
                case 0:
                    return;
            }
        }
    }


    /**
     * Edits order if need be
     * @param i order to edit
     */
    private void orderEdit(Order i) {

        int orderSelect;
        do {
            System.out.println("----------  Product in Order ---------");
            System.out.println("Name        Quantity       Price");
            System.out.println(i.toString());
            System.out.println("0 Done editing");
            orderSelect = getInput("Enter index Order : ", new input.IntegerInputGrabber());
            if (orderSelect != 0 && orderSelect >= 1 && (orderSelect - 1) <= i.GetOrderProduct().size() - 1) {
                int quantity = getInput("Enter Quantity : ", new input.IntegerInputGrabber());
                i.GetOrderProduct().get(orderSelect - 1).setQuantity(quantity);
            } else {
                System.out.println("Invalid code entered. Try again");
            }
        } while (orderSelect != 0);


    }


    /**
     * its a method to prompt users to choose the option if they choose ordermanagement in mainmenu
     */
    private void orderManage() {
        int menu;
        while (true) {
            System.out.println("Order Management\n\n 1. Print Order\n 2. Edit Order\n 3. Remove Order\n 4. Pay For Order\n 0. Exit\n\nChoose your option: ");
            menu = input.nextInt();
            input.nextLine();
            switch (menu) {

                /*
                 *print all the order details come out
                 */
                case 1:
                    if (OrderManagement.getInstance().getOrderArray().isEmpty())
                        System.out.println("Nothing is in here");
                    System.out.println("---- Order List ----");
                    System.out.println("Order   Quantity   Price");
                    Cashier.showOrders();//OrderManagement.getInstance().getOrderArray();
                    break;

                /*
                 * it is for edit order and show which one index want to edit
                 */
                case 2:
                    if (OrderManagement.getInstance().getOrderArray().isEmpty()) {
                        System.out.println("Nothing in order list");
                        break;
                    }
                    Cashier.showOrders();
                    int index = getInput("Enter index Order : ", new input.IntegerInputGrabber());
                    orderEdit(OrderManagement.getInstance().getOrderArray().get(index-1));
                    break;


                /*
                 *It is for the remove order and prompt user to enter which index want to remove
                 */
                case 3:
                    if (OrderManagement.getInstance().getOrderArray().isEmpty()) {
                        System.out.println("Nothing is in here");
                        break;
                    }
                    Cashier.showOrders();
                    int delete = getInput("Enter index Order : ", new input.IntegerInputGrabber());
                    OrderManagement.getInstance().DeleteOrder(OrderManagement.getInstance().getOrderArray().get(delete-1));
                    break;
                case 4:
                    if (OrderManagement.getInstance().getOrderArray().isEmpty()) {
                        System.out.println("Nothing is in here");
                        break;
                    }
                    Cashier.showOrders();
                    int pay = getInput("Enter index Order : ", new input.IntegerInputGrabber());
                    Cashier.payForOrder(OrderManagement.getInstance().getOrderArray().get(pay-1));
                    break;
                case 0:
                    return;
            }
        }
    }


    /**
     * It is for inventory option, prompt user which option that they wish to usr
     */
    private void inventory() {
        int menu;
        while (true) {
            System.out.print("Inventory Management\n\n 1. Print Stock\n 2. Add Stock\n 3. Remove Stock\n 0. Exit\n\nChoose your option: ");
            menu = input.nextInt();
            input.nextLine();
            switch (menu) {
                case 1:

                    /*
                     *print out all the stock details
                     */
                    System.out.println("---- Stock List ----");
                    System.out.println("Num  Name       Left");
                    StockManagement.getInstance().PrintStocks();
                    break;
                case 2:

                    /*
                     * add the stock into the index user want
                     */
                    // add stock here
                    if (StockManagement.getInstance().stocks.size() == 0)
                        addaStock();
                    else {
                        System.out.println("---- Stock List ----");
                        System.out.println("Num\tName\t\tLeft");
                        StockManagement.getInstance().PrintStocks();
                        System.out.println("0\tAdd Stock");
                        int stockselect = getInput("Enter index Add : ", new input.IntegerInputGrabber());

                        if (stockselect >= 1 && (stockselect - 1) >= StockManagement.getInstance().stocks.size() - 1) {
                            int[] prediction = Prediction.getInstance().getPrediction(StockManagement.getInstance().stocks.get(stockselect - 1).getName());
                            if (prediction[0] > 0) {
                                System.out.println("Recommended Amount of stock to add - " + prediction[0]);
                                System.out.println("Min - " + prediction[1] + " Max - " + prediction[2]);
                            }
                            int toadd = getInput("Enter number of \"" + StockManagement.getInstance().stocks.get(stockselect - 1).getName() + "\" to add - ", new input.IntegerInputGrabber());
                            StockManagement.getInstance().addStockQuantity(stockselect - 1, toadd);
                        } else {
                            input.nextLine();
                            addaStock();
                        }
                    }
                    break;
                case 3:

                    /*
                     *remove the stock
                     */
                    System.out.println("-- Stock List --");
                    if (StockManagement.getInstance().IsEmpty()) {
                        System.out.println("Nothing is in stock !");
                        break;
                    }
                    StockManagement.getInstance().PrintStocks();
                    System.out.println();
                    int selection = getInput("Enter index to delete : ", new input.IntegerInputGrabber());

                    if (selection != 0 && selection >= 1 && (selection - 1) <= StockManagement.getInstance().stocks.size() - 1) {
                        StockManagement.getInstance().DeleteStock(StockManagement.getInstance().stocks.get(selection - 1));
                    } else if (selection != 0 && ((selection - 1) >= (StockManagement.getInstance().stocks.size() - 1) || selection - 1 < 0)) {
                        System.out.println("Error Please Select Again.");
                    } else if (selection != 0) System.out.println("Error");

                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     *add the stock by prompt user to enter the stock details
     */
    private void addaStock() {
        System.out.print("Enter Name of Item : ");
        String name = input.nextLine();
        double price = getInput("Enter Price of " + name + " : ", new input.DoubleInputGrabber());
        int left = getInput("Enter Number of " + name + " in stock : ", new input.IntegerInputGrabber());
        StockManagement.getInstance().AddStock(name, price, left);
    }

    /**
     *
     * @param prompt message to show user
     * @param grabber input object
     * @param <T> type of input needed
     * @return input from user
     */
    static <T> T getInput(String prompt, input.InputGrabber<T> grabber) {
        System.out.print(prompt);
        do {
            if (grabber.hasNextInput(input)) {
                System.out.println(grabber.getExpectedInputFormat());
                System.out.print(prompt);
                input.nextLine();
            }
        } while (grabber.hasNextInput(input));
        return grabber.getNextInput(input);
    }

}