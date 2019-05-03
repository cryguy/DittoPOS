package DittoPOS.administration;
import java.util.HashMap;
import java.util.Iterator;


public class UserManagement {
	
    /*
    TODO : Use synchronized singleton or just keep as is
    TODO : Implement proper User Management. TBD
     */
	 
	HashMap<String,User> users = new HashMap<String,User>();
 
    private static UserManagement ourInstance = new UserManagement();

    private UserManagement getInstance() {
    	
        return ourInstance;
    }
    
    private UserManagement() {
    	
    }
    
    private void changePassword(String name,String password) {
    	for(String i : users.keySet()) {
    		if(i==name) 
    		users.get(i).setPassword(password);
    	}
    }
    
    private void changeName(String oldName,String newName) {
    	for(String i : users.keySet()) {
    		if(i==oldName) {
    		users.get(i).setName(newName);
    		i=newName;
    		}
    	}
    }
    
    private void changePerms(String name,Permissions perms) {
    	for(String i : users.keySet()) {
    		if(i==name)
    		users.get(i).setPermission(perms);
    	}
    }
    
    private void addUser(String name,String password,Permissions perms,String image) {
    	users.put(name,new User(name,password,perms,image));
    }
    
    private void addUser(String name,String password,Permissions perms) {
    	users.put(name,new User(name,password,perms));
    }
    
    private void addUser(String name,String password) {
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
}






























