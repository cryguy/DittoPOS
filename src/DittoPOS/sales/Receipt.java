package DittoPOS.sales;

import DittoPOS.products.ProductManagement;
import DittoPOS.products.SaleProduct;

import java.util.HashMap;

public class Receipt {
    HashMap<SaleProduct, Integer> resit = new HashMap<>();

    public Receipt(HashMap<SaleProduct, Integer> resit) {
        this.resit = (HashMap<SaleProduct, Integer>) resit.clone();
    }

    public Receipt() {
    }
    public HashMap<SaleProduct, Integer> getResit() {
        return resit;
    }

    public void addItem(SaleProduct saleProduct, int quantity) {


        resit.put(ProductManagement.getInstance().getProduct(saleProduct.getProduct().getName()), resit.getOrDefault(ProductManagement.getInstance().getProduct(saleProduct.getProduct().getName()), 0) + quantity); // add quantity to product
        if (resit.get(ProductManagement.getInstance().getProduct(saleProduct.getProduct().getName())) <= 0)
            resit.remove(saleProduct);
    }

    private void removeItem(SaleProduct saleProduct) {
        resit.remove(saleProduct);
    }


}


