package Work_1;

import java.io.Console;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {
    public static void main(String[] args) {
        Console cons = System.console();
        List<String> cartList = new LinkedList<>();
        String[] split_commands = new String[0];

        System.out.println("Welcome to your shopping cart");

        do {
            String command = cons.readLine("> ");
            split_commands = command.trim().toLowerCase().split(" ");

            if (split_commands.length > 0) {
                switch (split_commands[0]) {
                    // Function to list out cart items if non-empty
                    case "list":
                        if (cartList.size() == 0) {
                            System.out.println("Your cart is empty");
                        } else {

                            for (int i = 0; i < cartList.size(); i++) {
                                System.out.printf("%d. %s\n", i + 1, cartList.get(i));
                            }
                        }
                        break;

                    // Function to delete an item from the cart
                    case "delete":
                        // Handles exception when non-integer or out-of-bound number is entered
                        try {
                            String removedItem = cartList.remove(Integer.parseInt(split_commands[1]) - 1);
                            System.out.println(removedItem + " removed from cart");
                        } catch (Exception e) {
                            System.out.println(split_commands[1] + " is not a valid option");
                        }
                        break;

                    // Function to add items to the cart
                    case "add":
                        if (split_commands.length == 1) {
                            System.out.println("Warning! Please provide an item to add");
                        } else {
                            for (int i = 1; i < split_commands.length; i++) {
                                cartList.add(split_commands[i]);
                                System.out.println(split_commands[i] + " added to cart");
                            }
                        }
                        break;

                    // Exit the program
                    case "exit":
                        System.out.printf("Goodbye!\n\n");
                        break;

                    default:
                        System.err.println(command + " is not a valid command.");
                        break;
                }
            }
        } while (!split_commands[0].equals("exit"));
    }
}
