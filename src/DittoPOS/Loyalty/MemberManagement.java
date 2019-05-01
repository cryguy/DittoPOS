package DittoPOS.Loyalty;
import java.util.HashMap;
import java.util.ArrayList;

public class MemberManagement {
    private static MemberManagement instance = null;
    HashMap<Integer, Member> members = new HashMap<>();
    public synchronized static MemberManagement getInstance() {
        if(instance == null) {
            instance = new MemberManagement();
        }
        return instance;
    }

    //TODO; check if member exists


    boolean addMember (String name, String address, String email, String phoneno){
        Member newMember = new Member(name, address, email, phoneno);
        int nextid = members.size() + 1;
        members.put(nextid, newMember);
        return true;
    }

    public ArrayList findMember (String keyword){
        ArrayList<Member> found = new ArrayList<>();

        for (Member i:members.values()) {
            if (i.checkMember(keyword))
                found.add(i);
        }
        return found;
    }

}
