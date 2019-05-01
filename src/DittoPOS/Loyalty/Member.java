package DittoPOS.Loyalty;
import java.lang.*;


public class Member {
    String name;
    String address;
    String email;
    String phoneno;
    int points;

    public Member(String name, String address, String email, String phoneno) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneno = phoneno;
        points = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void incrementPoints(int points) {
        this.points += points;
    }

    public boolean checkMember(String keyword) {
        CharSequence searchKeyword = keyword;
        boolean bool = name.contains(keyword);
        bool = (bool | email.contains(keyword));
        bool = (bool | phoneno.contains(keyword));
        return bool;
    }
}
