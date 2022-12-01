package Work_1;

import java.io.Console;
import java.util.LinkedList;
import java.util.List;

public class ToDo_2 {
    public static void main(String[] args) {
        List<Integer> listOfInt = new LinkedList<>();
        Console cons = System.console();
        String item = "";

        while (true) {
            item = cons.readLine("Please enter a number: ");
            item = item.trim();

            if (item.equals("stop"))
                break;
            
            listOfInt.add(Integer.parseInt(item));
        }
        System.out.printf("Number of elements in the list: %d\n", listOfInt.size());
        for ( int i = 0; i < listOfInt.size(); i++) {
            System.out.printf("[%d]: %d\n", i, listOfInt.get(i));
        }
    }
}
