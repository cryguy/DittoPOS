package DittoPOS.administration;

import DittoPOS.helpers.Json;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Iterator;


public class UserManagement {
	
    /*
    TODO : Use synchronized singleton or just keep as is
    TODO : Implement proper User Management. TBD
     */

    public HashMap<String, User> users = new HashMap<>();

    public User loggedin = null;
	private static UserManagement instance = null;

	public synchronized static UserManagement getInstance() {
		if(instance == null) {
			instance = new UserManagement();
		}
		return instance;
	}


    private UserManagement() {
    	
    }

    /**
     * restore the object using json data
     *
     * @param json the input of json string
     */
    public void setUsers(String json) {
        users = Json.a.fromJson(json, new TypeToken<HashMap<String, User>>() {
        }.getType());
    }
    private void changeName(String oldName,String newName) {
    	for(String i : users.keySet()) {
    		if(i==oldName) {
    		users.get(i).setName(newName);
    		i=newName;
    		}
        }
    }


    public void addUser(String name, String password, String image) {
        users.put(name, new User(name, password,image));
    }
    
    void addUser(String name,String password) {
    	users.put(name,new User(name,password));
    }

    
    Iterator<String> iterator = users.keySet().iterator();

    private void DeleteUser(String name) {
        while(iterator.hasNext()){
            String user = iterator.next();
            if(user.contains(name)){
                iterator.remove();
            }
        }
    }

    /**
     * serialize the current object into json
     *
     * @return String of json
     */
    public String toJson() {
        return Json.a.toJson(users);
    }

}






























