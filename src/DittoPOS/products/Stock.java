package DittoPOS.products;


/*
TODO : Decide if we want to keep this in, we can have a array of Stock Batches, we can have multiple batches of stock with diff expiry
 */


import java.util.Date;

class Stock {
    /*
    TODO: Allow negative stock
     */



    private int left;
    private Date expiry;
    private int minCount=0;

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
     * @return Date object with expiry
     */
    Date getExpiry() { return this.expiry; }

    /**
     * get minimum count
     * @return
     */
    int getMinCount(){
        return this.minCount;
    }

    /**
     * set minimum count
     * @param min
     */
    void setMinCount(int min){
        this.minCount = min;
    }

    /**
     * check if existing stock is below minimum
     * @return
     */
    boolean islow(){
        return this.left<=this.minCount;
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