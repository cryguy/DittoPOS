package DittoPOS.reports;

import DittoPOS.helpers.Json;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CashFlow {

    final static DecimalFormat df = new DecimalFormat("0.00");
    private final static CashFlow ourInstance = new CashFlow();
    ArrayList<String> log = new ArrayList<>();
    ArrayList<Double> moneylog = new ArrayList<>();

    private CashFlow() {
    }

    public static CashFlow getInstance() {
        return ourInstance;
    }

    public void addToList(String reason, double amount) {

        this.log.add(reason);
        this.moneylog.add(Double.valueOf(df.format(amount)));
    }

    public void reset() {
        this.log.clear();
        this.moneylog.clear();
    }

    public ArrayList<String> getLog() {
        return log;
    }

    public void setLog(String json) {
        log = Json.a.fromJson(json, new TypeToken<ArrayList<String>>() {
        }.getType());
    }

    public ArrayList<Double> getMoneylog() {
        return moneylog;
    }

    public void setMoneylog(String json) {
        moneylog = Json.a.fromJson(json, new TypeToken<ArrayList<Double>>() {
        }.getType());
    }

    /**
     * serialize the current object into json
     *
     * @return String of json
     */
    public String toJsonRe() {
        return Json.a.toJson(log);
    }

    /**
     * serialize the current object into json
     *
     * @return String of json
     */
    public String toJsonCash() {
        return Json.a.toJson(moneylog);
    }
}
