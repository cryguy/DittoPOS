package DittoPOS.products;

import java.util.ArrayList;

public class TaxManagement {
    private static ArrayList<Tax> tax = new ArrayList<>();
    private static TaxManagement instance = null;

    private TaxManagement(){
    }

    public synchronized static TaxManagement getInstance() {
        if(instance == null) {
            instance = new TaxManagement();
        }
        return instance;
    }

    private void addTax(String name, double percentage){
        tax.add(new Tax(name,percentage));

    }

    private boolean removeTax(Tax name){

        return tax.remove(name);
    // remember to REFRESH after using this, otherwise GG

    }

    private void editTax(String name,double percentage,boolean prepost){

        for (Tax i : tax){
            if (i.getName().equals(name)) {
                i.setPercentage(percentage);
                i.setPrepost(prepost);
            }
        }
    }

    private ArrayList<Tax> listTax(){
        return tax;
    }

}
