package Work_1;

import java.io.Console;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {
    public static void main(String[] args) {
        Console cons = System.console();
        List<String> cartList = new LinkedList<>();

        System.out.println("Welcome to your shopping cart");

        while (true) {
            String command = cons.readLine("> ");
            // command = command.trim().toLowerCase().split(" ");

            if (command.equals("list")) {
                if (cartList.size() == 0) {
                    System.out.println("Your cart is empty");
                } else {
                    
                    for (int i = 0; i < cartList.size(); i++ ) {
                        System.out.printf("%d. %s", i+1, cartList.get(i));
                    }
                }
            }

            // switch (command.trim().toLowerCase()) {
            // case "list":
            // continue;

            // case (command.contains("delete")):
            // continue;

            // case "add":
            // continue;

            // default:
            // System.err.println(command + " is not a valid command.");
            // }
        }
    }
}
