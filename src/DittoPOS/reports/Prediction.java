package DittoPOS.reports;
import com.github.signaflo.timeseries.TimePeriod;
import com.github.signaflo.timeseries.TimeSeries;
import com.github.signaflo.timeseries.Ts;
import com.github.signaflo.timeseries.forecast.Forecast;
import com.github.signaflo.timeseries.model.arima.Arima;
import com.github.signaflo.timeseries.model.arima.ArimaOrder;
import java.util.ArrayList;


/*
Not sure if we want to keep this in
 */

public class Prediction {

    private final ArimaOrder modelOrder = ArimaOrder.order(0, 1, 1, 0, 1, 1);
    private ArrayList<String> TimeSeriesArrayName = new ArrayList<>();
    private ArrayList<TimeSeries> TimeSeries = new ArrayList<>();
    private ArrayList<String> forecastArrayName = new ArrayList<>();
    private ArrayList<Forecast> forecast = new ArrayList<>();

    private static Prediction instance = null;

    private Prediction() {
    }
    /**
     * make sure it will have only one can access to Prediction at the same time
     * and check the instance is it null, if it is, it will create a new Prediction object
     * @return object of Prediction
     */

    public synchronized static Prediction getInstance() {
        if (instance == null) {
            instance = new Prediction();
        }
        return instance;
    }

    /**
     * Add data to timeseries array
     * @param name name of data
     * @param data the actual data to do calculations on
     */
    public void addData(String name, double[] data) {
        TimeSeriesArrayName.add(name);
        TimeSeries.add(Ts.newWeeklySeries(data));
    }

    /**
     * reset data of timeseries in order to not interfere with existing data
     */
    private void resetData() {
        TimeSeriesArrayName.clear();
        TimeSeries.clear();
    }

    /**
     * run the prediction
     */
    private void runPrediction() {
        forecastArrayName.clear();
        forecast.clear();
        for (int counter=0;counter < TimeSeriesArrayName.size(); counter++)
        {
            forecastArrayName.add(TimeSeriesArrayName.get(counter));
            forecast.add(Arima.model(TimeSeries.get(counter), modelOrder, TimePeriod.halfMonth()).forecast(1));
        }
        resetData();
    }


    /**
     *print out the prediction of the ingredient that need to prepare
     */
    public void printPredictions() {
        runPrediction();
        System.out.println("---------- Prediction ----------");
        System.out.println("Name       Prediction LOW-UP-MID");
        for (int counter=0;counter < forecastArrayName.size(); counter++)
        {
            System.out.printf("%-11s", forecastArrayName.get(counter));
            System.out.printf("%10.0f  %2.0f %2.0f  %2.0f\n",
                    forecast.get(counter).pointEstimates().at(0),
                    forecast.get(counter).lowerPredictionInterval().at(0),
                    forecast.get(counter).upperPredictionInterval().at(0),
                    forecast.get(counter).pointEstimates().at(0));
        }
    }

    /**
     * Check if the array contains the name
     * @param name name to check
     * @return true if it exists
     */
    private boolean forecastContainsName (String name){
        for (String forecastName : forecastArrayName) {
            if (forecastName.equals(name))
                return true;
        }
        return false;
    }

    /**
     * get forecast of stock
     * @param name name of stock
     * @return forecast object
     */
    private Forecast getForecast(String name){
        int counter;
        for (counter=0;counter < forecastArrayName.size(); counter++)
        {
            if (forecastArrayName.get(counter).equals(name))
                break;
        }
        return forecast.get(counter);
    }

    /**
     * get the value Predicted
     * @param name name of stock to get prediction of
     * @return an array of integer
     */
    public int[] getPrediction(String name) {
        int[] a = new int[3];
        if (forecastContainsName(name)) // check if it exists in the map, if not we will crash
        {
            a[0] = (int) getForecast(name).pointEstimates().at(0);
            a[1] = (int) getForecast(name).lowerPredictionInterval().at(0);
            a[2] = (int) getForecast(name).upperPredictionInterval().at(0);
        }
        return a;
    }
}
