package MyFirstApp;

import java.io.Console;

public class ConsoleExample {
    public static void main(String[] args) {
        Console cons = System.console();
        String name = "";
        
        while (name.trim().length() <= 0) {
            name = cons.readLine("What is your name?  ");
            name = name.trim();

        }

        // to compare Strings, use 'equals' instead of operator '=='
        if (name.equals("fred")) {
            System.out.println("Boooo!!");
        } else {
            System.out.printf("Hello! %s ! Nice to meet you!\n\n", name); 
        }
    }
}
