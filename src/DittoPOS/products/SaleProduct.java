package DittoPOS.products;

import java.util.Date;
import java.util.HashMap;

public class SaleProduct {


    /*
    TODO: Decide on whether to have a concurrent hashmap or ???
    */
    // TODO : Add tax brackets
    private Product product;
    private HashMap<String, Stock> barcodeStock = new HashMap<>();

    public SaleProduct(String name, double price, boolean canExpire, String barcode, int stockleft)
    {
        product = new Product(name,price,canExpire);
    }


    public void addStock(int numStock, String barcode, Date date)
    {
        /**
         * TODO: Barcode should be appended? or just use a completely diff 1?
         */
        Stock stock = new Stock(numStock, date);
        barcodeStock.put(product.getBarcode(), stock);

        // what if expiry is same?
    }

    /**
     *
     * @param numStock number of stock to add
     * @param barcode unique string to add/append to the existing
     */
    public void addStock(int numStock, String barcode)
    {
        if (barcodeStock.isEmpty()) {
            Stock stock = new Stock(numStock);
            barcodeStock.put(product.getBarcode(), stock);
        }
        else
        {
            barcodeStock.get(product.getBarcode()).addLeft(numStock);
        }
    }

    public Product getProduct() {
        return product;
    }
}
