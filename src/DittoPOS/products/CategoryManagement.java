package DittoPOS.products;

import DittoPOS.helpers.Json;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class CategoryManagement {
    private static CategoryManagement instance = null;
    HashMap<String, Category> categories = new HashMap<>();

    public synchronized static CategoryManagement getInstance() { // synchronized singleton object - thread safe implementation , keyword here is in sync - only 1 thread can access it at a time
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
        return categories.getOrDefault(name, null);
    }

    public boolean deleteCategory(String name)
    {
        return categories.remove(name,categories.get(name));
    }

    public HashMap<String, Category> allCategory()
    {
        return this.categories;
    }

    /**
     * serialize the current object into json
     *
     * @return String of json
     */
    public String toJson() {
        return Json.a.toJson(categories);
    }

    /**
     * restore the object using json data
     *
     * @param json the input of json string
     */
    public void setCategories(String json) {
        categories = Json.a.fromJson(json, new TypeToken<HashMap<String, Category>>() {
        }.getType());
    }
}
