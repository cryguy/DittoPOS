package DittoPOS.products;


/*
TODO : Decide if we want to keep this in, we can have a array of Stock Batches, we can have multiple batches of stock with diff expiry
 */


import java.util.Date;

class Stock { // Stock is the child class of ingredient

    /*
    TODO : Implement this properly
    Not sure if we want to use Inheritance here,
    might be better to just use the object because we might need to add this to Product?
    Or we can just make a method to return an Object of Ingredient?

    */

    private int left;
    private Date expiry;

    /**
     * create a overloaded constructor to change the value in field name for store
     * @param expiry expiry date of stock
     * @param left number that is in stock
     */
    Stock(int left, Date expiry) {
        this.left = left;
        this.expiry = expiry;
        //this.ingredient = ingredient;
    }
    Stock(int left) {
        this.left = left;
        this.expiry = null;
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
     * get expiry date
     *
     * @return Date object with expiry
     */
    Date getExpiry() { return this.expiry; }


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