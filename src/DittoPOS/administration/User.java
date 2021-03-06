package DittoPOS.administration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;


public class User {

    /*
    TODO : Proper input validation and testing
     */


    private String name;
    private String image;
    private String password_hash;

    @Override
    public String toString() {
        return this.name;
    }

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


    public User(String name, String password, String image)
    {
    	setName(name);
    	setImage(image);
        setPassword(password, false);

    }

    public User (String name,String password)
    {

    	setName(name);
        setPassword(password, false);
    }

    static String passwordToHash(String password, String saltstr) {
        try {
            Random r = new SecureRandom();
            byte[] salt = new byte[20];
            r.nextBytes(salt);
            if (saltstr != null)
                if (!saltstr.isEmpty())
                    salt = toBytes(saltstr);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            return toBase64(salt) + "," + toBase64(digest.digest(concat(password.getBytes(), salt)));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    static byte[] concat(byte[]...arrays)
    {
        // Determine the length of the result array
        int totalLength = 0;
        for (int i = 0; i < arrays.length; i++)
        {
            totalLength += arrays[i].length;
        }

        // create the result array
        byte[] result = new byte[totalLength];

        // copy the source arrays into the result array
        int currentIndex = 0;
        for (int i = 0; i < arrays.length; i++)
        {
            System.arraycopy(arrays[i], 0, result, currentIndex, arrays[i].length);
            currentIndex += arrays[i].length;
        }

        return result;
    }

    public void setPassword(String password_hash, boolean salt) {

        String saltstr = null;
        if (salt)
            saltstr = password_hash.split(",")[0];
        String hashedpass = passwordToHash(password_hash, saltstr);
        this.password_hash = hashedpass;
    }

    static byte[] toBytes(String base64){
        return Base64.getDecoder().decode(base64);
    }

    static String toBase64(byte[] bytes)
    {
        return Base64.getEncoder().encodeToString(bytes);
    }

        
    public boolean checkPassword(String password)
    {
    	
    	String salt = password_hash.split(",")[0];
        String testhash = passwordToHash(password,salt);
        
        return (password_hash.equals(testhash)); // implement hashing and proper checking
        
    }
   
}


