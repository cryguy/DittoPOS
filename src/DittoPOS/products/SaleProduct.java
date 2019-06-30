package DittoPOS.products;

public class SaleProduct {

    private Product product;


    //private HashMap<String, Stock> barcodeStock = new HashMap<>();
    private int stock;
    private Tax tax;
    private int minStock;
    private int maxStock; // should we have this?

    SaleProduct(String name, double price, boolean canExpire, String barcode, int stockleft, Tax tax)
    {
        product = new Product(name,price,canExpire);
        this.product.setBarcode(barcode);
        this.tax = tax;
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // we should revisit this in the future
    /**
     *

    public void addStock(int numStock, String barcode)
    {
        //if (barcodeStock.isEmpty()) {
          //  Stock stock = new Stock(numStock);
          //  barcodeStock.put(product.getBarcode(), stock);
        }
        //else
        {
        //    barcodeStock.get(product.getBarcode()).addLeft(numStock);
        }
    }
     */
    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }
    public Product getProduct() {
        return product;
    }
}
