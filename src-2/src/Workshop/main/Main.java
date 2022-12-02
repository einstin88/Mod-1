package Workshop.main;

import Workshop.cart.Apple;
import Workshop.cart.Item;
import Workshop.cart.Orange;

public class Main {
    public static void main(String[] args) {
        // Item apple = new Item("apple", "APP");
        // Item orange = new Item("orange", "ORG");

        // apple.setPrice(.5f);
        // apple.setQuantity(10);

        // orange.setPrice(.5f);
        // orange.setQuantity(15);

        Apple apple = new Apple(); 
        Orange orange = new Orange();

        apple.setType("Washington");

        Item item = apple; // item does not have access to getType method

        // to access sub-class methods, cast it to the sub-class first
        Apple washington = (Apple) item;
        washington.setType("Washington");

        if (item instanceof Orange) {
            orange = (Orange) item;
        }
    }
}
