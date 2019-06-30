package DittoPOS.products;

import java.util.ArrayList;

public class Category {


    private ArrayList<SaleProduct> products = new ArrayList<>();

    public void addProductToCategory(SaleProduct Product)
    {
        products.add(Product);
    }

    public boolean removeProductFromCategory(SaleProduct Product)
    {
        return products.remove(Product);
    }

    public ArrayList<SaleProduct> getProductsInCategory()
    {
        return this.products;
    }

    public boolean categoryContainsProduct(SaleProduct Product) {
        return products.contains(Product);
    }

}
