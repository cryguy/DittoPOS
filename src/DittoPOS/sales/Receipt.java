package DittoPOS.sales;

import DittoPOS.products.SaleProduct;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

public class Receipt {
    HashMap<SaleProduct, Integer> resit = new HashMap<>();

    public HashMap<SaleProduct, Integer> getResit() {
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


