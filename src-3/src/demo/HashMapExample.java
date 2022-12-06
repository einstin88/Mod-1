package demo;

import java.util.ArrayList;
import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, Integer> myMap = new HashMap<>();
        HashMap<String, ArrayList<String>> newMap = new HashMap<>();

        // Add a key, value pair
        myMap.put("Pelie", 35);
        myMap.put("mark", 40);

        System.out.println(myMap.get("Pelie"));
        
        myMap.put("Pelie", 34);
        System.out.println(myMap.get("Pelie"));

        // Populate nested for of hashMap
        newMap.put("ken", new ArrayList<String>());
    }
}
