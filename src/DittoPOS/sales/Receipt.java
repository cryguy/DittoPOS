package DittoPOS.sales;

import DittoPOS.products.SaleProduct;

import java.util.ArrayList;
import java.util.HashMap;

public class Receipt {
    static HashMap<SaleProduct, Integer> resit = new HashMap<>();

    public static HashMap<SaleProduct, Integer> getResit() {
        return resit;
    }

    void addItem(SaleProduct saleProduct, int quantity) {
        resit.put(saleProduct, quantity);
    }

    private void removeItem(SaleProduct saleProduct) {
        resit.remove(saleProduct);
    }

    public Integer getQuantity(SaleProduct saleProduct) {
        return resit.getOrDefault(saleProduct, 0);
    }
}


