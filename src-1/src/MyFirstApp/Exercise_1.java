package MyFirstApp;

import java.io.Console;

public class Exercise_1 {
    public static void main(String[] args) {
        Console cons = System.console();
        Integer val = 0, count = 0;
        String input = "";

        while (true) {
            input = cons.readLine("Enter a number: ");
            input = input.trim();
            
            if (input.equals("stop")) {
                break;
            } else {
                try {
                    val += Integer.parseInt(input);
                    count++;
                } catch (Exception e) {
                    System.err.printf("%s is not a valid input! \n\n", input);
                }
            }
        }
        System.out.println("The total of "+ count + " number is " + val);
    }
}
