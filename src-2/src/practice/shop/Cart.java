package practice.shop;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Cart {
   // Create a type of list to store items in the cart
   private Set<Item> cart = new LinkedHashSet<Item>();
   private String userName;

   // Option to create cart with a username
   public Cart() {
   }

   public Cart(String userName) {
      this.userName = userName;
   }

   /*
    * CART METHODS
    * - add to put items into cart (must have item name. Optional to have quantity
    * and/or price)
    * - list to list out all the items in the cart
    * - checkout to output the total price of everything in the car
    */
   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   // Class methods
   // Options to add item in various forms
   public void addItem(String name) {
      Item newItem = new Item(name);
      pushItemToCart(newItem, name);
   }

   public void addItem(String name, int quantity) {
      Item newItem = new Item(name, quantity);
      pushItemToCart(newItem, name);
   }

   public void addItem(String name, int quantity, Float price) {
      Item newItem = new Item(name, quantity, price);
      pushItemToCart(newItem, name);
   }

   // Method to validate iteam before adding into cart
   private void pushItemToCart(Item newItem, String name) {
      if (this.cart.add(newItem)) {
         System.out.println(name + " added to cart");
      } else {
         System.out.println(name + " is already in your cart!");
      }
   }

   // Function to list items in the cart
   public void listItem() {
      if (this.cart.size() > 0) {
         Iterator<Item> iter = this.cart.iterator();

         for (int i = 1; iter.hasNext(); i++) {
            Item item = iter.next();
            System.out.printf("%5d. %s\tQuantity: %d\tPrice: $ %.2f\n", i, item.getName(), item.getQuantity(), item.getPrice());
         }
      } else {
         System.out.println("Your cart is empty.");
      }
   }

   // Function to sum the total price of the cart
   public float checkoutCart() {
      float total = 0.0f;

      if (this.cart.size() > 0) {
         Iterator<Item> iter = this.cart.iterator();

         while (iter.hasNext()) {
            Item item = iter.next();
            total += (item.getQuantity() * item.getPrice());
         }
      }
      return total;
   }

}
