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
    public void addCategory(String name)
    {
        categories.put(name,new Category());
    }
    public Category getCategory(String name)
    {
        return categories.get(name);
    }
    boolean deleteCategory(String name)
    {
        return categories.remove(name,categories.get(name));
    }
    public HashMap allCategory()
    {
        return this.categories;
    }
}
