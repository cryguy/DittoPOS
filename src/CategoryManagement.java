import DittoPOS.Category;

import java.util.HashMap;
import java.util.ArrayList;

public class CategoryManagement {
    private static CategoryManagement instance = null;
    HashMap<String, Category> categories = new HashMap<>();

    public synchronized static CategoryManagement getInstance() {
        if (instance == null) {
            instance = new CategoryManagement();
        }
        return instance;
    }
}
