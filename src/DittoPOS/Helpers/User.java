package DittoPOS.Helpers;

public class User {

    /*
    TODO : Proper input validation and testing
     */


    private String name;
    private String image;
    private Permissions permission;
    private String password_hash;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Permissions getPermission() {
        return permission;
    }

    public void setPermission(Permissions permission) {
        this.permission = permission;
    }


    public User (String name,String password, Permissions perms, String image)
    {
        /*
        TODO: Implement user adding
         */


    }

    public User (String name,String password, Permissions perms)
    {
        /*
        TODO: Implement user adding
         */


    }

    public User (String name,String password)
    {
        /*
        TODO: Implement user adding
         */

    }


    boolean checkPassword(String password)
    {

        /*
        TODO: Implement some password hashing
         */
        return (password.equals("correct")); // implement hashing and proper checking

    }





}
