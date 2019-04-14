package DittoPOS.Helpers;
import java.util.ArrayList;


/*
We can either heavily Change this around or idk?

TODO : Tax brackets - do we implement them in product management or here directly? We can have a tax management page or sumthin.
TODO : BUG Jacky for tax bracket implementation

TODO : ADD reference/shortname and Barcode!
 */


public class Product implements Cloneable {

    private String name;
    private double price;
    private int quantity;

    private ArrayList<Ingredient> ingredients;

    /**
     * overload constructor to receive the value from user
     * @param name name of product
     * @param price price of product
     * @param ingredients ingredients needed
     */
    Product(String name, double price, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.price = price;
        this.quantity = 1;
        this.ingredients = ingredients;
    }


    /**
     *get name of product
     * @return name of the product
     */
    String getName() {
        return this.name;
    }


    /**
     *get price of product
     * @return price of product
     */
    double getPrice() {
        return this.price;
    }

    /**
     *get quantity of product
     * @return quantity of product
     */
    int getQuantity()
    {
        return this.quantity;
    }

    /**
     * set the quantity of product
     * @param i number to set quantity to
     */

    void setQuantity(int i) {
        this.quantity = i;
    }

    /**
     *get the ingredients of product
     * @return the ingredient needed by the product
     */
    ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }


    /**
     *add the quantity of product
     * @param add number to add
     */
    void addQuantity(int add) {
        this.quantity += add;
    }
    public int reduceQuantityAndReturn(int reduce) {
        if (this.quantity <= reduce)
        {
            return -1;
        }
        else return reduce - this.quantity;
    }


    /**
     * Clone the product for use, if we dont do this, the adding of product into
     * order would be slower and would be using identifiers instead of directly using the object
     * @return a new Product object
     * @throws CloneNotSupportedException gives error if its not supported
     */
    public Product clone() throws CloneNotSupportedException {
        Product cloneObj = (Product) super.clone();
        cloneObj.ingredients = this.ingredients;
        cloneObj.quantity = 1;
        cloneObj.name = this.name;
        cloneObj.price = this.price;
        return cloneObj;
    }
}
