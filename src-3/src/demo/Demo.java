package demo;

import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) {
        System.out.println("Array List Demo");

        // Create ArrayList object
        ArrayList<String> myList = new ArrayList<>();

        // Add items to the list
        myList.add("apples");
        myList.add("oranges");

        // Loop over and print each item
        for (String item : myList) {
            System.out.println("Item -> " + item);
        }

        // Remove "apples"
        myList.remove("apples");

        // Print item count
        System.out.println(myList.size());
    }
}
