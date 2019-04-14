package DittoPOS.Helpers;


/*
TODO : Decide if we want to keep this in, we can have a array of Stock Batches, we can have multiple batches of stock with diff expiry
 */

class Stock extends Ingredient { // Stock is the child class of ingredient

    /*

    Not sure if we want to use Inheritance here,
    might be better to just use the object because we might need to add this to Product?
    Or we can just make a method to return an Object of Ingredient?

    */

    private int left;


    /**
     * create a overloaded constructor to change the value in field name for store
     * @param name name of stock to add
     * @param price price of stock
     * @param left number that is in stock
     */
    Stock(String name, double price, int left) {
        super(name, price);
        this.left = left;
        //this.ingredient = ingredient;
    }


    /**
     *get left of stock
     * @return left
     */
    int getLeft() {
        return this.left;
    }   // Through this method to get left value


    /**
     *get name of ingredient from the parent class
     * @return name of ingredient from parent class
     */
    String getName() {
        return super.GetName();
    }


    /**
     * get price of ingredient
     *
     * @return price of ingredient from parent class
     */
    double getPrice() {
        return super.GetPrice();
    }


    /**
     * reduce stock
     * @param reduce number to reduce by
     */
    void reduceLeft(int reduce) {
        this.left -= reduce;
    }


    /**
     * add number of stock left
     * @param add number to add
     */
    void addLeft(int add) {
        this.left += add;
    }
}