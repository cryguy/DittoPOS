package DittoPOS.products;

public class Tax {
    private String name;
    double percentage;
    boolean prepost; // 0 for before, 1 for after service charge

    //TODO : Find stuff that needs implementing?
    //TODO : Implement this properly

    Tax(String name, double percentage){
        this.name = name;
        this.percentage = percentage;
        this.prepost = true;
    }

    /**
     * typical setters and getters
     * @return
     */
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

    public void setPrepost(boolean prepost){ this.prepost = prepost; }

    public boolean getPrepost(){return this.prepost;}



}
