package DittoPOS.sales;

import java.util.ArrayList;
import java.util.HashMap;

public class Receipt {
    static ArrayList<Lines> lines = new ArrayList<>();

    void addLines(Lines line) {
        lines.add(line);
    }

    private boolean removeLine(int index) {
        return lines.remove(lines.get(index));
        //make sure to refresh
    }

    public Lines getLine(int index) {
        return lines.get(index);
    }

    ArrayList<Lines> listLine() {
        return lines;
    }


}
