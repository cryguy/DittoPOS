

package DittoPOS.products;
import DittoPOS.helpers.Json;
import DittoPOS.reports.Prediction;
import com.google.gson.reflect.TypeToken;

import java.util.*;

// TODO : Do a complete rewrite/refactor of the whole thing, this seems to be unneeded in our use case
/**
 * declare the variable of stockmanagement
 */
public class StockManagement {
    // StockManagement is an object that contains the array of Stocks
    public ArrayList<Stock> stocks = new ArrayList<>();
    private ArrayList<String> stockNameForDayCounter = new ArrayList<>();
    private ArrayList<Integer> dayCounter = new ArrayList<>();
    private ArrayList<String> stockNameForUsageCount = new ArrayList<>();
    private ArrayList<ArrayList<Double>> usageCount = new ArrayList<>();
    private static StockManagement instance = null;

    private StockManagement() {
    }
    /**
     * make sure it will have only one can access to StockManagement at the same time
     * and check the instance is it null, if it is, it will create a new StockManagement object
     * @return object of StockManagement
     */


    public synchronized static StockManagement getInstance() {
        if(instance == null) {
            instance = new StockManagement();
        }
        return instance;
    }


    /**
     *to check whether the stock is it empty
     * @return value of stocks
     */

    public boolean IsEmpty() {
        return stocks.isEmpty();
    }


    /**
     * for adding new stock and store them into the stocks with details
     * @param name name of the stock
     * @param price price of stock
     * @param left number in stock
     */

    public void AddStock(String name, double price, int left) {
        stocks.add(new Stock(name, price, left));
    }


    /**
     *get the stocks details
     */

    public void PrintStocks(){
        int x = 0;
        for (Stock i : stocks) {
            System.out.printf("%d     %20s %3d%n", ++x, i.getName(), i.getLeft());
            //System.out.println(++x + ". " + i.GetName() + " " + i.getLeft());
        }
    }


    /**
     *check if the ingredient used have stock and reduce the ingredient in stock
     * @param product ingredient to reduce from
     */
    void ReduceQuantity(Product product) {
        /*
        for (Ingredient i : product.getIngredients())
            for (Stock j : stocks)
                if (j.GetName().equals(i.GetName())) {
                    addToUsageArray(i.GetName(), i.GetNeeded() * product.getQuantity());
                    j.reduceLeft(i.GetNeeded() * product.getQuantity());
                    break;
                }
                */
    }

    /**
     *add the stock for ingredient quantity
     * @param index index of stock to add
     * @param numbertoadd number of stock to add
     */

    public void addStockQuantity(int index, int numbertoadd) {

        this.getStock().get(index).addLeft(numbertoadd);
    }


    private void putIfNotExistsIntoDay(String name){
        boolean exists = false;
        for (String aNameReferenceForDay : stockNameForDayCounter) {
            if (aNameReferenceForDay.equals(name)) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            stockNameForDayCounter.add(name);
            dayCounter.add(0);
        }
    }

    private int getDayFromReference(String name)
    {
        int counter;
        for (counter = 0 ; counter < stockNameForDayCounter.size(); counter ++){
            if (stockNameForDayCounter.get(counter).equals(name))
                break;
        }
        return dayCounter.get(counter);
    }
    private void incrementDay()
    {
        for (int i = 0; i < dayCounter.size(); i++){
            dayCounter.set(i, dayCounter.get(i)+1);
        }
    }

    /**
     * Ends the "day" by incrementing the daycounter so we know to keep the stock used in a separate "value"
     * and prints the prediction for tomorrows stock usage
     */
    void endDay() {

        incrementDay();
        for (int counter = 0; counter < stockNameForUsageCount.size(); counter++)
        {
            if (usageCount.get(counter).size() > 2){
                double[] target = new double[usageCount.get(counter).size()];
                for (int i = 0; i < target.length; i++) {
                    target[i] = usageCount.get(counter).get(i); // to unBox Double to double...
                }
                Prediction.getInstance().addData(stockNameForUsageCount.get(counter), target);
            }
        }
        Prediction.getInstance().printPredictions();

    }


    /**
     * Check if stock is already in the usage Array
     * @param name name of the stock to check if its in array for forecast
     * @return true or false depending if the item exists in the array
     */
    private boolean stockInUsageArray(String name){
        for (String aStockReference : stockNameForUsageCount) {
            if (aStockReference.equals(name))
                return true;
        }
        return false;
    }


    private ArrayList<Double> getUsageFromStockName(String name){
        int counter;
        for (counter = 0 ; counter < stockNameForUsageCount.size(); counter ++){
            if (stockNameForUsageCount.get(counter).equals(name))
                break;
        }
        return usageCount.get(counter);
    }

    private void putInUsageArray(String name, ArrayList<Double> stockused) {
      stockNameForUsageCount.add(name);
      usageCount.add(stockused);
    }
    private void addToUsageArray(String name, double numtoadd) {
        putIfNotExistsIntoDay(name);
        if (!stockInUsageArray(name)) {
            ArrayList<Double> arraylist = new ArrayList<>();
            arraylist.add(getDayFromReference(name), numtoadd);
            putInUsageArray(name, arraylist);
        } else {
            ArrayList<Double> arraylist = getUsageFromStockName(name);
            if (getDayFromReference(name) >= arraylist.size())
                arraylist.add(getDayFromReference(name), numtoadd);
            arraylist.set(getDayFromReference(name), arraylist.get(getDayFromReference(name)) + numtoadd);
        }
    }

    public void setStocks(String json) {
        instance = Json.a.fromJson(json, new TypeToken<StockManagement>() {
        }.getType());
    }

    public String toJson() {
        return Json.a.toJson(instance);
    }

    public void DeleteStock(Stock i) {
        stocks.remove(i);
    }

    private ArrayList<Stock> getStock() {
        return stocks;
    }


}

