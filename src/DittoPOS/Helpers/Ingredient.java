package DittoPOS.Helpers;

class Ingredient {

    /**
     * declare the datatype
     */


    private final String name;
    private final double price;
    final private int needed;


    /**
     * Overloaded constructor of Ingredient to store new name and new price of new object
     * @param name name of ingredient
     * @param price price of ingredient
     */

    Ingredient(String name, double price) {
        this.name = name;
        this.price = price;
        this.needed = 0;
    }


    /**
     *Overloaded constructor of Ingredient to store new name and new price and new needed of new object
     * @param name name of ingredient
     * @param price price of ingredient
     * @param needed number needed for product
     */

    Ingredient(String name, double price, int needed) {
        this.name = name;
        this.price = price;
        this.needed = needed;
    }

    String GetName() {
        return this.name;
    }

    double GetPrice() {
        return this.price;
    }

    int GetNeeded() {
        return this.needed;
    }


    /**
     *Turn the value of ingredient into string
     */

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", needed=" + needed +
                '}';
    }
}
