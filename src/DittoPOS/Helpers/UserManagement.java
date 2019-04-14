package DittoPOS.Helpers;

public class UserManagement {


    /*
    TODO : Use synchronized singleton or just keep as is
    TODO : Implement proper User Management. TBD
     */


    private static UserManagement ourInstance = new UserManagement();

    public static UserManagement getInstance() {
        return ourInstance;
    }

    private UserManagement() {
    }
}
