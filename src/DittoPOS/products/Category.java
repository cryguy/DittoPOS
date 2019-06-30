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
        removeProductFromCatByName(Product.getProduct().getName()); // remove every product matching name, as json serializing/deserializing causes some issues by using references
        return products.remove(Product);
    }

    public void removeProductFromCatByName(String name) {
        products.removeIf(saleProduct -> saleProduct.getProduct().getName().equals(name)); // java 8 predicate to remove everything that matches the rule
    }

    public ArrayList<SaleProduct> getProductsInCategory()
    {
        return this.products;
    }

    public void setProducts(ArrayList<SaleProduct> products) {
        this.products = products;
    }

    public boolean categoryContainsProduct(SaleProduct Product) {
        return products.contains(Product);
    }

}
