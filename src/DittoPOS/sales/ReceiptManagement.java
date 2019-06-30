package DittoPOS.sales;

import DittoPOS.helpers.Json;

import java.util.ArrayList;

public class ReceiptManagement {
    private static ArrayList<Receipt> receipt = new ArrayList<>();
    private static ReceiptManagement instance = null;
    private Receipt currentReceipt = new Receipt();

    private ReceiptManagement() {
    }

    public synchronized static ReceiptManagement getInstance() {
        if (instance == null) {
            instance = new ReceiptManagement();
        }
        return instance;
    }

    public void addReceiptAndClear(Receipt receipttoclone) {
        receipt.add(new Receipt(receipttoclone.resit));
        receipttoclone.resit.clear();
    }

    public Receipt getCurrentReceipt() {
        return currentReceipt;
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

    /**
     * serialize the current object into json
     *
     * @return String of json
     */
    public String toJson() {
        return Json.a.toJson(receipt);
    }


    /**
     * restore the object using json data
     *
     * @param json the input of json string
     */
    public void setReceipt(String json) {
        // receipt = Json.a.fromJson(json, new TypeToken<ArrayList<Receipt>>() {}.getType());
    }
}
