package DittoPOS.products;

import java.util.ArrayList;

public class BundleManagement {


    private static BundleManagement instance = null;
    private static ArrayList<Bundle> BundleList = new ArrayList<>();


    private BundleManagement() {
    }

    /**
     * Prevents us from making more than 1 instance and causing problems
     *
     * @return the object of BundleManagement
     */

    public synchronized static BundleManagement getInstance() {
        if (instance == null) {
            instance = new BundleManagement();
        }
        return instance;
    }


    public void addBundle(String name) {
        BundleList.add(new Bundle(name));
    }
}
//TODO: remove bundle
