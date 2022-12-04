package practice;

import java.io.Console;

import practice.shop.Cart;

public class Main {
    public static void main(String[] args) {
        Console cons = System.console();
        Cart myCart = new Cart();
        String[] split_commands = new String[0];

        System.out.println("Welcome to your shopping cart");

        do {
            String command = cons.readLine("> ");
            split_commands = command.trim().toLowerCase().split(" ");

            if (split_commands.length > 0) {
                switch (split_commands[0]) {
                    case "add":
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

                    case "list":
                        myCart.listItem();
                        break;

                    case "checkout":
                        System.out.printf("$ %.2f\n", myCart.checkoutCart());
                        break;

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
