package dot_product;

import java.io.Console;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Parse CLI arguments
        Integer rows = Integer.parseInt(args[0]);
        Integer cols = Integer.parseInt(args[1]);
        System.out.printf(">> DOT PRODUCT operation on matrix with %d rows and %d columns%n", rows, cols);

        // Variables to store user inputs
        List<List<Integer>> matrix = new LinkedList<>();
        List<Integer> weights = new LinkedList<>();

        // Get user inputs for matrix
        Console cons = System.console();
        for (int i = 0 ; i < rows ; i++) {
            // Split the inputs to the array and store in a temporary list
            String[] rowInputs = cons.readLine(">> Row %d: \n<< ", i).trim().split(" ");
            List<Integer> rowInputsList = new LinkedList<>();

            for (int j = 0 ; j < cols ; j++) {
                rowInputsList.add(Integer.parseInt(rowInputs[j]));
            }
            // View what we have processed before adding to our matrix
            System.out.println(rowInputsList);
            matrix.add(rowInputsList);
        }
        System.out.println(">> Your matrix is:");
        System.out.println(matrix + "\n");

        // Get the weights
        String[] weightInputs = cons.readLine(">> Input the weights: \n<< ").trim().split(" ");
        for (int i = 0 ; i < cols ; i++) {
            weights.add(Integer.parseInt(weightInputs[i]));
        }
        System.out.println(">> Your weights is:");
        System.out.println(weights + "\n");

        // Perform dot product
        List<Integer> results = new LinkedList<>();
        for (int i = 0 ; i < rows ; i++){
            Integer sum = 0;
            for (int j = 0 ; j < cols ; j++) {
                sum += matrix.get(i).get(j) * weights.get(j);
            }
            System.out.printf(">> Sum for row %d is %d%n", i, sum);
            results.add(sum);
        }

        System.out.println(">> The final result is:");
        System.out.println(results);
    }
}
