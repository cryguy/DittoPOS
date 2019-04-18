package DittoPOS.products;

public class Tax {
    private String name;


    double percentage;

    //TODO : Find stuff that needs implementing?
    //TODO : Implement this properly

    Tax(String name, double percentage){
        this.name = name;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }


}
