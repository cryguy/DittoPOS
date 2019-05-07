package DittoPOS.products;

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
    void addCategory(String name)
    {
        categories.put(name,new Category());
    }
    Category getCategory(String name)
    {
        return categories.get(name);
    }
    boolean deleteCategory(String name)
    {
        return categories.remove(name,categories.get(name));
    }
    HashMap allCategory()
    {
        return this.categories;
    }
}
