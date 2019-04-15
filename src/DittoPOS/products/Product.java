package DittoPOS.products;


/*
We can either heavily Change this around or idk?

TODO : Tax brackets - do we implement them in product management or here directly? We can have a tax management page or sumthin.
TODO : BUG Jacky for tax bracket implementation

TODO : ADD reference/shortname and Barcode!
 */


public class Product implements Cloneable {

    private String name;
    private String longname;
    private String reference;
    private String barcode;
    private double price;
    private boolean canExpire = false; // default to false - as in product that wont go bad/expire

    /**
     * overload constructor to receive the value from user
     * @param name name of product
     * @param price price of product
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Constructor for product that can expire
     * @param name
     * @param price
     * @param canExpire
     */

    public Product(String name, double price, boolean canExpire) {
        this.name = name;
        this.price = price;
        this.canExpire = canExpire;
    }

    /**
     *get name of product
     * @return name of the product
     */
    public String getName() {
        return this.name;
    }


    /**
     *get price of product
     * @return price of product
     */
    public double getPrice() {
        return this.price;
    }


    /**
     * check if product can expire
     * @return true if product can expire
     */

    public boolean isCanExpire() {
        return canExpire;
    }

    /**
     * Set if product can expire
     * @param canExpire true/false depending on need
     */
    public void setCanExpire(boolean canExpire) {
        this.canExpire = canExpire;
    }

    // TODO : Add documentation/comments
    public String getLongname() {
        return longname;
    }

    public void setLongname(String longname) {
        this.longname = longname;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }



    /**
     * Clone the product for use, if we dont do this, the adding of product into
     * order would be slower and would be using identifiers instead of directly using the object
     * @return a new Product object
     * @throws CloneNotSupportedException gives error if its not supported
     */
    public Product clone() throws CloneNotSupportedException {
        Product cloneObj = (Product) super.clone();
        cloneObj.name = this.name;
        cloneObj.price = this.price;
        return cloneObj;
    }
}
