package practice;

import java.io.Console;

import practice.shop.Cart;

public class Main {
    // Declare constants
    public static final String ADD = "add";
    public static final String CHECKOUT = "checkout";
    public static final String DELETE = "delete";
    public static final String EXIT = "exit";
    public static final String LIST = "list";
    public static final String SAVE = "save";

    public static final String DEFAULT_DB = "db";

    public static void main(String[] args) {
        Console cons = System.console();
        Cart myCart = new Cart();
        String[] split_commands = new String[0];

        System.out.println("Welcome to your shopping cart");
        System.out.println(args.length);


        do {
            String command = cons.readLine("> ");
            split_commands = command.trim().toLowerCase().split(" ");

            if (!split_commands[0].equals("")) {
                switch (split_commands[0]) {
                    case ADD:
                        if (split_commands.length == 1) {
                            System.out.println("Warning! Please provide an item to add");
                        } else if (split_commands.length == 2) {
                            try {
                                myCart.addItem(split_commands[1]);
                            } catch (Exception e) {
                                System.err.println(split_commands[1] + " has to be a string");
                            }
                        } else if (split_commands.length == 3) {
                            try {
                                myCart.addItem(split_commands[1], Integer.parseInt(split_commands[2]));
                            } catch (Exception e) {
                                System.err.println(split_commands[1] + " has to be a string");
                                System.err.println(split_commands[2] + " has to be an integer");
                            }
                        } else if (split_commands.length == 4) {
                            try {
                                myCart.addItem(split_commands[1], Integer.parseInt(split_commands[2]), Float.parseFloat(split_commands[3]));
                            } catch (Exception e) {
                                System.err.println(split_commands[1] + " has to be a string");
                                System.err.println(split_commands[2] + " has to be an integer");
                                System.err.println(split_commands[3] + " has to be a float");
                            }
                        } else {
                            System.out.println("Warning! Input format is invalid");
                        }
                        break;

                    case LIST:
                        myCart.listItem();
                        break;

                    case CHECKOUT:
                        System.out.printf("$ %.2f\n", myCart.checkoutCart());
                        break;

                    case EXIT:
                        System.out.printf("Goodbye!\n\n");
                        break;

                    default:
                        System.err.println(command + " is not a valid command.");
                        break;
                }
            }
        } while (!split_commands[0].equals(EXIT));

    }
}
