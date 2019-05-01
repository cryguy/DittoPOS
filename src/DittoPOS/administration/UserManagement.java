package DittoPOS.administration;

public class UserManagement {
	
	


    /*
    TODO : Use synchronized singleton or just keep as is
    TODO : Implement proper User Management. TBD
     */
	
    private static UserManagement ourInstance = new UserManagement();

    private UserManagement getInstance() {
    	
        return ourInstance;
    }
    
    private UserManagement() {
    	
    }
    
    private void changePassword(String name) {
    	
    }
    
    private void changeName(String name) {
    	
    }
    
    private void changePerms(Permissions perms) {
    	
    }
    
    private void AddUser(String name,String password,Permissions perms,String image) {
    	new User(name,password,perms,image);
    }
    
    private void AddUser(String name,String password,Permissions perms) {
    	new User(name,password,perms);
    }
    
    private void AddUser(String name,String password) {
    	new User(name,password);
    }
    
    private void DeleteUser(String name) {

    }
    
    
}
