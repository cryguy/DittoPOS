package DittoPOS.sales;

import DittoPOS.products.ProductManagement;
import DittoPOS.products.SaleProduct;

import java.util.HashMap;

public class Receipt {
    HashMap<SaleProduct, Integer> resit = new HashMap<>();

    public HashMap<SaleProduct, Integer> getResit() {
        return resit;
    }

    public void addItem(SaleProduct saleProduct, int quantity) {


        resit.put(ProductManagement.getInstance().getProduct(saleProduct.getProduct().getName()), resit.getOrDefault(ProductManagement.getInstance().getProduct(saleProduct.getProduct().getName()), 0) + quantity); // add quantity to product
    }

    private void removeItem(SaleProduct saleProduct) {
        resit.remove(saleProduct);
    }


}


