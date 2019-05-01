package DittoPOS.sales;

import java.util.ArrayList;

public class ReceiptManagement {
    static ArrayList<Receipt> receipt = new ArrayList<>();
    private static ReceiptManagement instance = null;

    private ReceiptManagement() {
    }

    public synchronized static ReceiptManagement getInstance() {
        if (instance == null) {
            instance = new ReceiptManagement();
        }
        return instance;
    }

    private Receipt getReceipt(int index) {
        return receipt.get(index);
    }

    private Receipt removeReceipt(int index) {
        return receipt.remove(index);
    }

    ArrayList<Receipt> listReceipt() {
        return receipt;
    }


}
