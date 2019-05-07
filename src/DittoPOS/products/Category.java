package DittoPOS.products;

import DittoPOS.products.SaleProduct;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Category {


    public ArrayList<SaleProduct> products = new ArrayList<>();
    void addProductToCategory(SaleProduct Product)
    {
        products.add(Product);
    }
    boolean removeProductFromCategory(SaleProduct Product)
    {
        return products.remove(Product);
    }
    ArrayList getProductsInCategory()
    {
        return this.products;
    }
    boolean categoryContainsProduct(SaleProduct Product){
        return products.contains(Product);
    }

}
