package practice.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import practice.shop.Cart;
import practice.shop.Item;

public class FileHandler {

    public static void Setup(String dirPath) {
        Path path = Paths.get(dirPath);
        File fileObj = path.toFile();
        fileObj.isDirectory();
    }

    public static Cart LoadFile(String filePath) {
        Path path = Paths.get(filePath);
        File fileObj = path.toFile();

        Cart userCart = new Cart();

        if (!fileObj.exists()) {
            SaveFile(filePath, userCart);
            return userCart;
        }

        try {
            FileReader fr = new FileReader(fileObj);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {
                String[] lineData = line.split(",");
                userCart.addItem(lineData[0], Integer.parseInt(lineData[1]) , Float.parseFloat(lineData[2]));
            }

            br.close();
            
        } catch (IOException e) {
            System.out.println("Error loading file --> " + filePath);
        } 

        return userCart;
    }


    public static void SaveFile(String filePath, Cart cart) {
        try {
            FileWriter fw = new FileWriter(filePath, false);

            if (!(cart.cart.size() > 0)) {
                for (Item item : cart.cart) {
                    fw.write(item.getName() + "," + item.getQuantity() + "," + item.getPrice() + "\n");
                }
            }
            fw.flush();
            fw.close();

        } catch (IOException e) {

        }
    }
}
