package DittoPOS.sales;

import DittoPOS.products.ProductManagement;
import DittoPOS.products.SaleProduct;

import java.util.HashMap;

public class Receipt {
    HashMap<SaleProduct, Integer> resit = new HashMap<>();


    @SuppressWarnings("unchecked")
    Receipt(HashMap<SaleProduct, Integer> resit) {
        this.resit = (HashMap<SaleProduct, Integer>) resit.clone();// we are cloning itself into the same type, so dont care about the warning.
    }

    Receipt() { // default initializer
    }
    public HashMap<SaleProduct, Integer> getResit() {
        return resit;
    }

    public void addItem(SaleProduct saleProduct, int quantity) {


        resit.put(ProductManagement.getInstance().getProduct(saleProduct.getProduct().getName()), resit.getOrDefault(ProductManagement.getInstance().getProduct(saleProduct.getProduct().getName()), 0) + quantity); // add quantity to product
        if (resit.get(ProductManagement.getInstance().getProduct(saleProduct.getProduct().getName())) <= 0)
            resit.remove(saleProduct); // remove item if its 0
    }

}


